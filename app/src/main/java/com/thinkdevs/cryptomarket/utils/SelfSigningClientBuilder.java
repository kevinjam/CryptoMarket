package com.thinkdevs.cryptomarket.utils;

/**
 * Created by kevinjanvier on 27/03/2018.
 */

import android.annotation.SuppressLint;
import android.content.Context;

import com.thinkdevs.cryptomarket.R;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class SelfSigningClientBuilder {

	public static OkHttpClient createClient(Context context) {

		OkHttpClient client = null;

		CertificateFactory cf = null;
		InputStream cert = null;
		Certificate ca = null;
		SSLContext sslContext = null;
		try {
			cf = CertificateFactory.getInstance("X.509");

			ca = cf.generateCertificate(cert);
			cert.close();

			String keyStoreType = KeyStore.getDefaultType();
			KeyStore keyStore = KeyStore.getInstance(keyStoreType);
			keyStore.load(null, null);
			keyStore.setCertificateEntry("ca", ca);

			String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
			tmf.init(keyStore);

			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, tmf.getTrustManagers(), null);

			client = new OkHttpClient.Builder()
					.sslSocketFactory(sslContext.getSocketFactory())
					.build();

		} catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException | KeyManagementException e) {
			e.printStackTrace();
		}

		return client;
	}

}