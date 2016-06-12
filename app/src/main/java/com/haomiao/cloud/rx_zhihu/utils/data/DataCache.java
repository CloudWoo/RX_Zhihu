package com.haomiao.cloud.rx_zhihu.utils.data;

import android.util.Log;

import com.haomiao.cloud.rx_zhihu.CApplication;
import com.haomiao.cloud.rx_zhihu.presenter.MainPresenter;
import com.haomiao.cloud.rx_zhihu.utils.DateUtil;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.utils.data.
 * Created by Cloud on 16/6/6.
 * Instruction
 */
public class DataCache {



    private int cacheNum = 0;
    DiskLruCache.Snapshot snapShot;
    public <T> void cacheData(final ArrayList<T> data, final String CACHE_KEY) {
        Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                DataCache.saveObject(data, CACHE_KEY);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                            @Override
                            public void call(Void v) {
                                Log.d("Cloud", "cache file " + CACHE_KEY + " successful");
                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        });



    }

    public <T> Observable<T> getCacheFile(final String CACHE_KEY, final int mState) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if(mState == MainPresenter.STATE_REFRESH){
                    subscriber.onNext(null);
                }else {
                    ObjectInputStream objectInputStream = null;
                    try {
                        String key = hashKeyForDisk(CACHE_KEY);
                        DiskLruCache.Snapshot snapShot = CApplication.getDiskLruCache().get(key);
                        if (snapShot != null) {
                            InputStream is = snapShot.getInputStream(0);
                            objectInputStream = new ObjectInputStream(is);
                            subscriber.onNext((T)objectInputStream.readObject());
                        }else {
                            subscriber.onNext(null);
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                        subscriber.onNext(null);
                    }
                }
                subscriber.onCompleted();
            }
        });
    }

    public <T> Observable<T> getCacheFile(final String CACHE_KEY) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                    ObjectInputStream objectInputStream = null;
                    try {
                        String key = hashKeyForDisk(CACHE_KEY);
                        DiskLruCache.Snapshot snapShot = CApplication.getDiskLruCache().get(key);
                        if (snapShot != null) {
                            InputStream is = snapShot.getInputStream(0);
                            objectInputStream = new ObjectInputStream(is);
                            subscriber.onNext((T)objectInputStream.readObject());
                        }else {
                            subscriber.onNext(null);
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                        subscriber.onNext(null);
                    }
                subscriber.onCompleted();
            }
        });
    }



    public  Observable<Integer> getFirstCacheFile() {

        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
//                cacheNum++;
//                subscriber.onNext(cacheNum);
//                subscriber.onCompleted();
                ObjectInputStream objectInputStream = null;
                try {
                    String key = hashKeyForDisk(DateUtil.getInstance().getDate(cacheNum));
                    snapShot = CApplication.getDiskLruCache().get(key);
                    if (snapShot != null) {
                        InputStream is = snapShot.getInputStream(0);
                        objectInputStream = new ObjectInputStream(is);
                        subscriber.onNext(cacheNum);
                    }else {
                        subscriber.onNext(null);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onNext(null);
                }
                cacheNum ++ ;
            subscriber.onCompleted();
            }
        }).repeat(100).first(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return snapShot != null;
            }
        });
    }



    private static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }


    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }



    private static void saveObject(Serializable object, String cacheKey){
        ObjectOutputStream out = null;
        try {
                    String key = hashKeyForDisk(cacheKey);
                    DiskLruCache.Editor editor = CApplication.getDiskLruCache().edit(key);
                    if (editor != null) {
                        OutputStream outputStream = editor.newOutputStream(0);
                        try {
                            out = new ObjectOutputStream(new BufferedOutputStream(outputStream, 8 * 1024));
                            out.writeObject(object);
                            editor.commit();
                        } catch (final IOException e) {
                            e.printStackTrace();
                            editor.abort();
                        } finally {
                            try {
                                if (out != null) {
                                    out.close();
                                }
                            } catch (final IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    CApplication.getDiskLruCache().flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

