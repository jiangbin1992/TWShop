package com.example.administrator.twshop.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

public class ImageUtil {
	// 保存图片本地路径
	public static final String ACCOUNT_DIR = Environment
			.getExternalStorageDirectory().getPath() + "/account_gcy/";
	public static final String ACCOUNT_MAINTRANCE_ICON_CACHE = "icon_gcy/";
	private static final String IMGPATH = ACCOUNT_DIR
			+ ACCOUNT_MAINTRANCE_ICON_CACHE;
	public static final String IMAGE_FILE_NAME = "icon_gcy.jpeg";
	public static final int REQUEST_GALLERY = 0xa0;
	public static final int REQUEST_CAMERA = 0xa1;
	public static final int RE_GALLERY = 127;
	public static final int RE_CAMERA = 128;

	private static ImageUtil instance = null;
	public static final String IMAGE_PATH = IMGPATH + IMAGE_FILE_NAME;

	public static ImageUtil getCropHelperInstance() {
		if (instance == null) {
			instance = new ImageUtil();
			creatFile();
		}
		return instance;
	}

	public void sethandleResultListerner(CropHandler handler, int requestCode,
			int resultCode, Intent data) {
		if (handler == null)
			return;
		if (resultCode == Activity.RESULT_CANCELED) {
			handler.onCropCancel();

		} else if (resultCode == Activity.RESULT_OK) {
			Bitmap photo = null;

			switch (requestCode) {
			case RE_CAMERA:
				if (data == null || data.getExtras() == null) {
					handler.onCropFailed("CropHandler's context MUST NOT be null!");
					return;
				}
				photo = data.getExtras().getParcelable("data");
				try {
					photo.compress(Bitmap.CompressFormat.JPEG, 80,
							new FileOutputStream(new File(IMGPATH,
									IMAGE_FILE_NAME)));
				} catch (Exception e) {
					e.printStackTrace();
					photo.recycle();
				}
				handler.onPhotoCropped(photo, requestCode);
				break;
			case RE_GALLERY:
				if (data == null || data.getExtras() == null) {
					handler.onCropFailed("CropHandler's context MUST NOT be null!");
					return;
				}
				photo = data.getExtras().getParcelable("data");
				try {
					photo.compress(Bitmap.CompressFormat.JPEG, 30,
							new FileOutputStream(new File(IMGPATH,
									IMAGE_FILE_NAME)));
				} catch (Exception e) {
					e.printStackTrace();
					photo.recycle();
				}
				handler.onPhotoCropped(photo, requestCode);
				break;
			case REQUEST_CAMERA:
				Intent intent = buildCropIntent(Uri.fromFile(new File(IMGPATH,
						IMAGE_FILE_NAME)));
				if (handler.getContext() != null) {
					handler.getContext().startActivityForResult(intent,
							RE_CAMERA);
				} else {
					handler.onCropFailed("CropHandler's context MUST NOT be null!");
				}
				break;
			case REQUEST_GALLERY:
				if (data == null) {
					handler.onCropFailed("Data MUST NOT be null!");
					return;
				}
				Intent intent2 = buildCropIntent(data.getData());

				if (handler.getContext() != null) {
					handler.getContext().startActivityForResult(intent2,
							RE_GALLERY);
				} else {
					handler.onCropFailed("CropHandler's context MUST NOT be null!");
				}
				break;
			}
		}
	}

	public Intent buildGalleryIntent() {
		Intent galleryIntent = new Intent();
		galleryIntent.setAction(Intent.ACTION_PICK);
		galleryIntent.setType("image/*");
		return galleryIntent;
	}

	public Intent buildCameraIntent() {
		Intent cameraIntent = new Intent();
		cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		if (hasSdcard()) {
			cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(new File(IMGPATH, IMAGE_FILE_NAME)));
		}
		return cameraIntent;
	}

	/***
	 * 判断sd卡
	 * 
	 * @return
	 */
	private boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		return state.equals(Environment.MEDIA_MOUNTED);
	}

	private Intent buildCropIntent(Uri uri) {
		Intent cropIntent = new Intent("com.android.camera.action.CROP");
		cropIntent.setDataAndType(uri, "image/*");
		cropIntent.putExtra("crop", "true");
		cropIntent.putExtra("aspectX", 1);
		cropIntent.putExtra("aspectY", 1);
		cropIntent.putExtra("outputX", 350);
		cropIntent.putExtra("outputY", 350);

		cropIntent.putExtra(MediaStore.EXTRA_OUTPUT,
				Uri.fromFile(new File(IMGPATH, IMAGE_FILE_NAME)));
		cropIntent.putExtra("outputFormat",
				Bitmap.CompressFormat.JPEG.toString());
		cropIntent.putExtra("noFaceDetection", true);
		cropIntent.putExtra("return-data", true);
		return cropIntent;
	}

	public interface CropHandler {
		void onPhotoCropped(Bitmap photo, int requesCode);

		void onCropCancel();

		void onCropFailed(String message);

		Activity getContext();
	}

	public static void creatFile() {
		File directory = new File(ACCOUNT_DIR);
		File imagepath = new File(IMGPATH);
		if (!directory.exists()) {
			directory.mkdir();
		}
		if (!imagepath.exists()) {
			imagepath.mkdir();
		}
		File fileone = new File(IMGPATH, IMAGE_FILE_NAME);
		try {
			if (!fileone.exists()) {
				fileone.createNewFile();
			}
		} catch (Exception e) {
		}
	}

	public static void imageLoader(Context context, String url,
                                   ImageView imageView) {
		if (context == null) {
			Log.e("imageLoader", " Context must NOT be empty!");
			return;
		}
		url = url.startsWith("http://") ? url : "file://" + url;
		Log.d("imageLoader", " 图片Url： " + url);
//
//		Glide.with(context)
//		.load(url)
//		.placeholder(R.drawable.person_head)
//		.error(R.drawable.person_head)
//		.dontAnimate()
//		.centerCrop()
//		.into(imageView);



	}
}
