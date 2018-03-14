package com.honeybeeapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import java.io.File;

public class F {
	/** 程序在存储器上的文件夹名称 */
	private static final String APP_FILE_NAME 		= "avazu_sphere_buy";
	/** 默认缓存数据库的目录 */
	private static final String DEFAULT_DATA  		= "database";
	/** 默认缓存图片的目录 */
	private static final String DEFAULT_IMAGE 		= "cache";
	/** 默认存放视频相关的目录 */
	private static final String	DEFAULT_VIDEO 		= "video";
	/** 默认截屏图片存放目录 */
	private static final String DEFAULT_SHOT  		= "screenshot";
	/** 默认文件下载的目录 */
	private static final String	DEFAULT_DOWNLOAD 	= "download";
	/** 默认Log文件存放目录 */
	private static final String	DEFAULT_LOGCAT		= "logcat";
	/** 默认IM即时聊天缓存目录 */
	private static final String DEFAULT_IM			= "im";

//	private static File		bootFile;				// 根目录文件
	private static Context 	mContext;
	private static String	mBootPath;
	private static File 	logcatFile		= null;// 日志保存路径
	private static File 	databaseFile	= null;// 数据缓存路径
	private static File 	imageFile 		= null;// 图片缓存路径
	private static File 	videoFile		= null;// 视频播放路径
	private static File 	screenFile		= null;// 截屏图片路径
	private static File 	downloadFile	= null;// 下载保存路径
	private static File		imFile			= null;// 即时聊天路径
	private static final String PATH_SEPARATOR=File.separator;
	
	
	public static void initBootDirectory(Context context, String bootDirectory) {
		mContext = context;
		mBootPath = (bootDirectory != null && bootDirectory.trim().length() != 0) ? bootDirectory : APP_FILE_NAME;
//		bootFile = creatDirectory(bootDirectory, context);
	}
	
	public static File getLogcatFile() {
		if (logcatFile == null) {
			logcatFile = createDirectory(mContext, mBootPath + PATH_SEPARATOR + DEFAULT_LOGCAT);
		}
		return logcatFile;
	}
	
	public static File getDatabaseFile() {
		if (databaseFile == null) {
			databaseFile = createDirectory(mContext, mBootPath + PATH_SEPARATOR + DEFAULT_DATA);
		}
		return databaseFile;
	}
	
	public static File getImageFile() {
		if (imageFile == null) {
			imageFile = createDirectory(mContext, mBootPath + PATH_SEPARATOR + DEFAULT_IMAGE);
		}
		return imageFile;
	}
	
	public static File getVideoFile() {
		if (videoFile == null) {
			videoFile = createDirectory(mContext, mBootPath + PATH_SEPARATOR + DEFAULT_VIDEO);
		}
		return videoFile;
	}
	
	public static File getScreenShotFile() {
		if (screenFile == null) {
			screenFile = createDirectory(mContext, mBootPath + PATH_SEPARATOR + DEFAULT_SHOT);
		}
		return screenFile;
	}
	
	public static File getDownloadFile() {
		if (downloadFile == null) {
			downloadFile = createDirectory(mContext, mBootPath + PATH_SEPARATOR + DEFAULT_DOWNLOAD);
		}
		return downloadFile;
	}
	
	public static File getIMFile() {
		if (imFile == null) {
			imFile = createDirectory(mContext, mBootPath + PATH_SEPARATOR + DEFAULT_IM);
		}
		return imFile;
	}
	
	public static File getVideoFile(String userID) {
		return createDirectory(mContext, mBootPath + PATH_SEPARATOR + DEFAULT_VIDEO + PATH_SEPARATOR + userID);
	}
	
	/**
	 * 创建目录，在根（/sdcard）目录下创建
	 * @return
	 */
	public static File createDirectory(Context mContext, String directory) {
		File dirFile = null;
		if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			dirFile =  new File(mContext.getCacheDir(), directory);
		} else {
			dirFile = new File(Environment.getExternalStorageDirectory(), directory);
		}
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		return dirFile;
	}
	
	/**
	 * 创建文件，在指定的目录下创建文件
	 * @param dirFile
	 * @param fileName
	 * @return
	 */
	public static File createNewFile(File dirFile, String fileName) {
		File newFile = new File(dirFile, fileName);
		try {
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			return newFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newFile;
	}
	
	/**
	 * 计算文件的大小
	 * @param file
	 * @return
	 */
	public static long calculateFileSize(File file) {
		long size = 0L;
		try {
			File[] files = file.listFiles();
			for (File newFile : files) {
				if (newFile.isDirectory()) {
					size += calculateFileSize(newFile);
				} else {
					size += newFile.length();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}
	
	/**
	 * 删除文件
	 * @param file
	 */
	public static void deleteFile(File file) {
		try {
			if (file.isFile()) {// 是文件
				file.delete();
				return;
			}
			File[] files = file.listFiles();
			for (File newFile : files) {
				deleteFile(newFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除含有指定名称的文件
	 * @param file
	 * @param name
	 */
	public static void deleteFile(File file, String name) {
		try {
			if (file.isFile()) {// 是文件
				if (name != null && name.trim().length() != 0 && file.getName().contains(name)) {
					file.delete();
				}
				return;
			}
			File[] files = file.listFiles();
			for (File newFile : files) {
				deleteFile(newFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static String getCacheDir() {
//	return C.context.getCacheDir().getPath() + File.separator + APP_FILE_NAME + File.separator;
//}

//public static boolean isSdcardExist() {
//	return FolderHelper.isSdcardExist();
//}

//public static String getAppPath() {
//	if (appPath == null)
//		initFoler();
//	FolderHelper.checkFolder(appPath);
//	return appPath;
//}

//public static String getLogPath() {
//	FolderHelper.checkFolder(getLogcatPath());
//	return logcatPath;
//}

//public static String getImagePath() {
//	if (imagePath == null)
//		initFoler();
//	FolderHelper.checkFolder(imagePath);
//	return imagePath;
//}

//public static String getDatabasePath() {
//	if (getUserPath() == null)
//		return null;
//	if (databasePath == null)
//		databasePath = getUserPath() + DatabaseFileName + File.separator;
//	FolderHelper.checkFolder(databasePath);
//	return databasePath;
//}

//public static String getFilePath() {
//	if (getUserPath() == null)
//		return null;
//	// if (filePath == null)
//	filePath = getUserPath() + DownloadFileName + File.separator;
//	FolderHelper.checkFolder(filePath);
//	return filePath;
//}
//
//public static String getUserPath() {
//	return userPath;
//}
//
//public static void setUserPath(String userPath) {
//	F.userPath = userPath;
//}

//public static void clearCacheDir() {
//	FolderHelper.deleteFile(getCacheDir());
//}

//public static void deleteFile(String path) {
//	FolderHelper.deleteFile(path);
//}

//public static void clear() {
//	FolderHelper.deleteFile(getImagePath());
//
//	getImagePath();
////	if (getUserPath() != null)
////		FolderHelper.deleteFile(getUserPath(), getFilePath());
////	FolderHelper.checkFolder(getUserPath());
//	getDatabasePath();
//}

//private static String UPDATE_DIR_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "Download";

//public static String getUpdateDir() {
//	if (FolderHelper.checkFolder(UPDATE_DIR_PATH)) {
//		return UPDATE_DIR_PATH;
//	} else {
//		return Environment.getExternalStorageDirectory().getPath();
//	}
//}
//
//public static void setUpdateDir(String updateDir) {
//	UPDATE_DIR_PATH = updateDir;
//}
public static boolean isNetworkAvailable(Context context) {
	ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	if (cm == null) {
		return false;
	}
	NetworkInfo[] infos = cm.getAllNetworkInfo();
	if (infos == null) {
		return false;
	}
	for (NetworkInfo info : infos) {
		if (info.getState() == NetworkInfo.State.CONNECTED) {
			return true;
		}
	}
	return false;
//		return true;
}
}
