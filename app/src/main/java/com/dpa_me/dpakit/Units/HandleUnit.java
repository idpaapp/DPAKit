package com.dpa_me.dpakit.Units;

        import android.Manifest;
        import android.animation.ObjectAnimator;
        import android.animation.PropertyValuesHolder;
        import android.animation.ValueAnimator;
        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.pm.PackageInfo;
        import android.content.pm.PackageManager;
        import android.content.res.Resources;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.ColorMatrix;
        import android.graphics.ColorMatrixColorFilter;
        import android.graphics.Typeface;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.drawable.Drawable;
        import android.net.Uri;
        import android.os.AsyncTask;
        import android.os.Build;
        import android.os.Environment;
        import android.os.Handler;
        import android.os.Message;
        import android.os.StrictMode;
        import android.preference.PreferenceManager;
        import android.provider.Settings;
        import android.text.Editable;
        import android.text.Layout;
        import android.text.TextWatcher;
        import android.util.Base64;
        import android.util.DisplayMetrics;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.WindowManager;
        import android.view.animation.AlphaAnimation;
        import android.view.animation.Animation;
        import android.view.animation.AnimationSet;
        import android.view.animation.AnimationUtils;
        import android.view.animation.DecelerateInterpolator;
        import android.view.animation.LinearInterpolator;
        import android.view.animation.RotateAnimation;
        import android.view.animation.Transformation;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.ProgressBar;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.RequiresApi;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;
        import androidx.fragment.app.Fragment;
        import androidx.viewpager.widget.ViewPager;

        import com.dpa_me.dpakit.Dialogs.ProgressDialog;
        import com.dpa_me.dpakit.R;
        import com.google.android.material.snackbar.Snackbar;
        import com.squareup.picasso.Picasso;

        import net.glxn.qrgen.android.QRCode;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.entity.StringEntity;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicNameValuePair;
        import org.apache.http.params.BasicHttpParams;
        import org.apache.http.params.HttpConnectionParams;
        import org.apache.http.protocol.HTTP;
        import org.apache.http.util.ByteArrayBuffer;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedInputStream;
        import java.io.BufferedReader;
        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.UnsupportedEncodingException;
        import java.lang.reflect.Field;
        import java.net.HttpURLConnection;
        import java.net.InetAddress;
        import java.net.URL;
        import java.net.URLConnection;
        import java.nio.channels.FileChannel;
        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;
        import java.text.DecimalFormat;
        import java.text.DecimalFormatSymbols;
        import java.text.NumberFormat;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
        import java.util.Random;

        import static android.widget.ImageView.ScaleType.CENTER_CROP;

public class HandleUnit {
    public static String current;
    public static String ApplicationID;
    public static ProgressDialog mProgressDialog;
    public static int xdpi = 0;
    public static int ydpi = 0;
    public static float DisplayDensityDpi = 0;
    public static Activity mMainPageActivity;
    public static String InvoiceNo;
    public static String Authority;
    public static int ResultOfPayment;
    public static String LocalPath;

    public static final int MY_PERMISSIONS = 123;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static class  HandleAnimations{
        public static ObjectAnimator BounceAnimation(View view, int duration) {
            PropertyValuesHolder scalex = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.8f);
            PropertyValuesHolder scaley = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.8f);
            ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(view, scalex, scaley);
            anim.setRepeatCount(ValueAnimator.INFINITE);
            anim.setRepeatMode(ValueAnimator.REVERSE);
            anim.setDuration(duration);
            anim.start();
            return anim;
        }

        public static ObjectAnimator BounceAnimation(View view, float offset, int duration) {
            PropertyValuesHolder scalex = PropertyValuesHolder.ofFloat(View.SCALE_X, offset);
            PropertyValuesHolder scaley = PropertyValuesHolder.ofFloat(View.SCALE_Y, offset);
            ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(view, scalex, scaley);
            view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            anim.setRepeatCount(ValueAnimator.INFINITE);
            anim.setRepeatMode(ValueAnimator.REVERSE);
            anim.setDuration(duration);
            anim.start();
            return anim;
        }

        public static void ShakeAnimation(Context mContext, View v, int duration) {
            Animation shake = AnimationUtils.loadAnimation(mContext, R.anim.shake_anim);
            shake.setDuration(duration);
            shake.setRepeatCount(2);
            v.startAnimation(shake);
        }

        public static void RotateAnimation(View v, int duration) {

            Animation anim = new RotateAnimation(0, 360,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(duration);
            anim.setRepeatCount(Animation.INFINITE);
            anim.setInterpolator(new LinearInterpolator());

            v.startAnimation(anim);
        }

        public static void RotateAnimation(View v, int duration, float pivote) {

            Animation anim = new RotateAnimation(0, 360,
                    Animation.RELATIVE_TO_SELF, pivote, Animation.RELATIVE_TO_SELF, pivote);
            anim.setDuration(duration);
            anim.setRepeatCount(Animation.INFINITE);
            anim.setInterpolator(new LinearInterpolator());

            v.startAnimation(anim);
        }

        public static void GlowAnimation(View v, int duration, int startOffset) {
            AlphaAnimation blinkanimation = new AlphaAnimation(0.5f, 0.2f);
            blinkanimation.setDuration(duration);
            blinkanimation.setInterpolator(new LinearInterpolator());
            blinkanimation.setRepeatCount(Animation.INFINITE);
            blinkanimation.setRepeatMode(Animation.REVERSE);
            blinkanimation.setStartOffset(startOffset);
            v.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            v.setAnimation(blinkanimation);
            v.startAnimation(blinkanimation);
        }

        public static void FadeInAnimation(View v, int duration, int startOffset) {
            Animation fadeIn = new AlphaAnimation(0, 1);
            fadeIn.setInterpolator(new DecelerateInterpolator());
            fadeIn.setDuration(duration);
            fadeIn.setStartOffset(startOffset);

            AnimationSet animation = new AnimationSet(false);
            animation.addAnimation(fadeIn);
            v.setAnimation(animation);
            v.startAnimation(animation);
        }

        public static void FadeInAnimation(View v, int duration, int startOffset, Runnable mRun) {
            Animation fadeIn = new AlphaAnimation(0, 1);
            fadeIn.setInterpolator(new DecelerateInterpolator());
            fadeIn.setDuration(duration);
            fadeIn.setStartOffset(startOffset);

            AnimationSet animation = new AnimationSet(false);
            animation.addAnimation(fadeIn);
            v.setAnimation(animation);
            v.startAnimation(animation);

            new Handler().postDelayed(mRun, startOffset + 1000);
        }

        public static void FadeOutAnimation(View v, int duration, int startOffset) {
            Animation fadeOut = new AlphaAnimation(1, 0);
            fadeOut.setInterpolator(new DecelerateInterpolator());
            fadeOut.setDuration(duration);
            fadeOut.setStartOffset(startOffset);

            AnimationSet animation = new AnimationSet(false);
            animation.addAnimation(fadeOut);
            v.setAnimation(animation);
            v.startAnimation(animation);
        }

        public static void FadeOutAnimation(View v, int duration, int startOffset, Runnable mRun) {
            Animation fadeOut = new AlphaAnimation(1, 0);
            fadeOut.setInterpolator(new DecelerateInterpolator());
            fadeOut.setDuration(duration);
            fadeOut.setStartOffset(startOffset);

            AnimationSet animation = new AnimationSet(false);
            animation.addAnimation(fadeOut);
            v.setAnimation(animation);
            v.startAnimation(animation);

            mRun.run();
        }

        public static void JumpInAnimation(Context mContext, View v, int duration, int startOffset) {
            Animation slideIn = AnimationUtils.loadAnimation(mContext, R.anim.fab_scale_up);
            slideIn.setInterpolator(new DecelerateInterpolator());
            slideIn.setDuration(duration);
            slideIn.setStartOffset(startOffset);
            v.setAnimation(slideIn);
            v.startAnimation(slideIn);
        }

        public static void JumpInAnimation(Context mContext, View v, int duration, int startOffset, final Runnable mRun) {
            Animation slideIn = AnimationUtils.loadAnimation(mContext, R.anim.fab_scale_up);
            slideIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mRun.run();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            slideIn.setDuration(duration);
            slideIn.setStartOffset(startOffset);
            v.setAnimation(slideIn);
            v.startAnimation(slideIn);
        }

        public static void Expand(final View v) {
            v.measure(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            final int targetHeight = v.getMeasuredHeight();

            // Older versions of android (pre API 21) cancel animations for views with a height of 0.
            v.getLayoutParams().height = 1;
            v.setVisibility(View.VISIBLE);


            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    v.getLayoutParams().height = interpolatedTime == 1
                            ? RelativeLayout.LayoutParams.WRAP_CONTENT
                            : (int) (targetHeight * interpolatedTime);
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            // 1dp/ms
            a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
            v.startAnimation(a);
        }

        public static void Collapse(final View v) {
            final int initialHeight = v.getMeasuredHeight();

            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    if (interpolatedTime == 1) {
                        v.setVisibility(View.GONE);
                    } else {
                        v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                        v.requestLayout();
                    }
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };

            // 1dp/ms
            a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
            v.startAnimation(a);
        }

        public static void setProgressWithAnimation(ProgressBar pb, int progress, int duration) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofInt(pb, "progress", progress);
            objectAnimator.setDuration(duration);
            objectAnimator.setInterpolator(new DecelerateInterpolator());
            objectAnimator.start();
        }
    }

    public static class HandleImages {
        public static void applyGrayScale(ImageView imgview) {
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            imgview.setColorFilter(filter);
        }

        public static String toBase64(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(compressFormat, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }

        public static String toBase64(ImageView imageView, Bitmap.CompressFormat compressFormat) {
            Bitmap bmp = drawableToBitmap(imageView.getDrawable());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bmp.compress(compressFormat, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }

        public static String toBase64(ImageView imageView) {
            Bitmap bmp = drawableToBitmap(imageView.getDrawable());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }

        public static String toBase64(String url) {
            try {
                URL imageUrl = new URL(url);
                URLConnection ucon = imageUrl.openConnection();
                InputStream is = ucon.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int read;
                while ((read = is.read(buffer, 0, buffer.length)) != -1) {
                    baos.write(buffer, 0, read);
                }
                baos.flush();
                return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public static String toBase64(Bitmap bitmap) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }

        public static String toBase64(Context mContext, Uri uri) {
            try {
                InputStream imageStream = mContext.getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                return Base64.encodeToString(byteArray, Base64.DEFAULT);
            } catch (Exception ex) {
                return "";
            }
        }

        public static Drawable drawableFromUrl(Context mContext, String url) {
            Bitmap x;
            try {

                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

                InputStream input = connection.getInputStream();
                connection.connect();
                x = BitmapFactory.decodeStream(input);
                return new BitmapDrawable(mContext.getResources(), x);
            } catch (Exception e) {
                e.printStackTrace();
                return mContext.getResources().getDrawable(R.drawable.ic_launcher);
            }
        }

        public static Bitmap drawableToBitmap(Drawable drawable) {
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }

            final int width = !drawable.getBounds().isEmpty() ? drawable
                    .getBounds().width() : drawable.getIntrinsicWidth();

            final int height = !drawable.getBounds().isEmpty() ? drawable
                    .getBounds().height() : drawable.getIntrinsicHeight();

            final Bitmap bitmap = Bitmap.createBitmap(width <= 0 ? 1 : width,
                    height <= 0 ? 1 : height, Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);

            return bitmap;
        }

        public static byte[] BitmapToByte(Bitmap bitmap) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return stream.toByteArray();
        }

        public static byte[] DrawableToByte(Drawable drawable) {
            Bitmap bitmap = drawableToBitmap(drawable);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            return byteArray;
        }

        public static void SetImageViewPicToOtherImageView(ImageView SourceView, ImageView DestinationView) {
            DestinationView.setImageBitmap(((BitmapDrawable) SourceView.getDrawable()).getBitmap());
        }

        public static void LoadBase64InImageView(ImageView imageView, String base64, boolean HideOnNoImage, boolean ShowLogoNoImage) {
            if (!base64.equals("''") && !base64.equals("")) {
                byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageView.setImageBitmap(decodedByte);
                imageView.setScaleType(CENTER_CROP);
                if (imageView.getTag() != null) {
                    if (imageView.getTag().toString().equals("0")) imageView.setTag("1");
                } else imageView.setTag("1");
            } else if (HideOnNoImage)
                imageView.setVisibility(View.GONE);
            else if (ShowLogoNoImage)
                imageView.setImageResource(R.drawable.ic_launcher);
        }

        public static void LoadBase64InImageView(ImageView imageView, String base64) {
            if (!base64.equals("''") && !base64.equals("")) {
                byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageView.setImageBitmap(decodedByte);
                if (imageView.getTag() != null) {
                    if (imageView.getTag().toString().equals("0")) imageView.setTag("1");
                } else imageView.setTag("1");
            }
        }

        public static Drawable LoadBase64InDrawable(Context mContext, String base64, boolean ShowLogoNoImage) {
            if (!base64.equals("''") && !base64.equals("")) {
                byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                return new BitmapDrawable(mContext.getResources(), decodedByte);
            } else if (ShowLogoNoImage)
                return mContext.getResources().getDrawable(R.drawable.ic_launcher);
            else return null;
        }

        public static void LoadBase64InImageView(ImageView imageView, String base64, boolean HideOnNoImage, boolean ShowLogoNoImage, ImageView.ScaleType scaleType) {
            if (!base64.equals("''") && !base64.equals("")) {
                byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageView.setImageBitmap(decodedByte);
                imageView.setScaleType(scaleType);
                if (imageView.getTag() != null) {
                    if (imageView.getTag().toString().equals("0")) imageView.setTag("1");
                } else imageView.setTag("1");
            } else if (HideOnNoImage)
                imageView.setVisibility(View.GONE);
            else if (ShowLogoNoImage)
                imageView.setImageResource(R.drawable.ic_launcher);
        }

        public static String SaveBitmapToFile(Context mContext, Bitmap bmp, String FileName) {
            String PicUri = "";
            try {
                if (LocalPath == null)
                    HandleApplication.SetLocalPath(mContext);
                String PicURL = FileName + ".jpg";
                File to = new File(LocalPath, FileName + ".jpg");
                PicUri = to.getPath();
                OutputStream fOut = new FileOutputStream(to);

                bmp.compress(Bitmap.CompressFormat.JPEG, 50, fOut);
                fOut.flush();
                fOut.close();
            } catch (Exception e) {
                Toast.makeText(mContext, "Error occured. Please try again later.",
                        Toast.LENGTH_SHORT).show();
            }

            return PicUri;
        }

        public static Bitmap LoadBitmapFromFile(String FileName) {
            File imgFile = new File(FileName);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inSampleSize = 6;
            options.inDither = true;

            return BitmapFactory.decodeFile(imgFile.getPath(), options);
        }

        public static Bitmap LoadImageFromURL(URL url) throws IOException {
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }

        public static Bitmap GenerateQRCode(String txt){
            return QRCode.from(txt).withColor(0xFF000000, 0xFFFFFFFF).bitmap();
        }

        public static Bitmap GenerateQRCode(String txt, int onColor, int offColor){
            return QRCode.from(txt).withColor(onColor, offColor).bitmap();
        }

        public static void ShareLink(Activity activity,  String subject, String body){
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            share.putExtra(Intent.EXTRA_SUBJECT, subject);
            share.putExtra(Intent.EXTRA_TEXT, body);
            activity.startActivity(Intent.createChooser(share, "اشتراک گذاری"));
        }
    }

    public static class HandleString {
        public static String CreateInputJSON(String[] tokenList, String[] valueList) {
            try {
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < tokenList.length; i++) {
                    jsonObject.put(tokenList[i], PersianNumStrToEnglish(valueList[i]));
                }

                return jsonObject.toString();
            } catch (Exception ex) {
                return "";
            }
        }

        public static String implode(String separator, ArrayList<String> data) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.size() - 1; i++) {
                if (!data.get(i).matches(" *")) {
                    sb.append(data.get(i));
                    sb.append(separator);
                }
            }
            sb.append(data.get(data.size() - 1).trim());
            return sb.toString();
        }

        public static String SimpleHash(String AStr, int Mode) {
            ApiCrypter apiCrypter = new ApiCrypter();
            try {
                return ApiCrypter.bytesToHex(apiCrypter.encrypt(AStr));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }

        public static String ReplacePersianToDigit(String str) {
            str = str.replaceAll("[,.٬]", "");
            str = PersianNumToEnglish(str);
            return str;
        }

        public static String getAlpha(int digit) {
            switch (digit) {
                case 1:
                    return "اول";
                case 2:
                    return "دوم";
                case 3:
                    return "سوم";
                case 4:
                    return "چهارم";
                case 5:
                    return "پنجم";
                case 6:
                    return "ششم";
                case 7:
                    return "هفتم";
                case 8:
                    return "هشتم";
                case 9:
                    return "نهم";
                case 10:
                    return "دهم";
                default:
                    return "";
            }
        }

        public static String ClearSigns(String str) {
            str = str.replaceAll("[,.٬]", "");
            str = PersianNumToEnglish(str);
            return str;
        }

        public static String PersianNumStrToEnglish(String number) {
            final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";
            int j = 0;
            char[] chars = new char[number.length()];
            for (int i = 0; i < number.length(); i++)
                chars[i] = ' ';

            for (int i = 0; i < number.length(); i++) {
                char ch = number.charAt(i);
                if (ch >= 0x0660 && ch <= 0x0669)
                    ch -= 0x0660 - '0';
                else if (ch >= 0x06f0 && ch <= 0x06F9)
                    ch -= 0x06f0 - '0';

                chars[j++] = ch;
            }
            return new String(chars).trim();
        }

        public static String PersianNumToEnglish(String number) {

            final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";
            int j = 0;
            char[] chars = new char[number.length()];
            for (int i = 0; i < number.length(); i++)
                chars[i] = ' ';

            for (int i = 0; i < number.length(); i++) {
                char ch = number.charAt(i);
                if (ch >= 0x0660 && ch <= 0x0669)
                    ch -= 0x0660 - '0';
                else if (ch >= 0x06f0 && ch <= 0x06F9)
                    ch -= 0x06f0 - '0';

                if (Character.isDigit(ch))
                    chars[j++] = ch;
            }
            return new String(chars).trim();
        }

        public static String SetThousandSeparator(String Value) {
            String cleanString = ReplacePersianToDigit(Value);
            if (cleanString.length() > 0) {
                double parsed = Double.parseDouble(cleanString);
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) formatter).getDecimalFormatSymbols();
                decimalFormatSymbols.setCurrencySymbol("");
                ((DecimalFormat) formatter).setDecimalFormatSymbols(decimalFormatSymbols);
                formatter.setMaximumFractionDigits(0);
                return formatter.format(parsed);
            } else {
                return cleanString;
            }
        }

        public static String QoutedStr(String txt) {
            return "'" + txt + "'";
        }

        public static String md5(String string) {
            byte[] hash;

            try {
                hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Huh, MD5 should be supported?", e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Huh, UTF-8 should be supported?", e);
            }

            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xFF) < 0x10) {
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString();
        }

        public static String HashString(String Str) {
            return BCrypt.hashpw(Str, BCrypt.gensalt()).replace("$2a$10$", "$2y$10$");
        }

        public static boolean CheckHash(String Str, String HashText) {
            String HashPass = HashText.replace("$2y$10$", "$2a$10$");
            return BCrypt.checkpw(Str, HashPass);
        }

        public static String if1Char(String chr) {
            if (chr.length() == 1)
                return "0" + chr;
            else return chr;
        }

        public static String ifNull(String Value, String rValue) {
            if (Value == null)
                return rValue;


            else {
                if (Value.equals(""))
                    return rValue;
                else
                    return Value;
            }
        }

        public static void AddCurrencyTextWatcher(final EditText v) {
            v.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!s.toString().equals(current)) {
                        v.removeTextChangedListener(this);

                        String cleanString = ReplacePersianToDigit(s.toString());
                        if (cleanString.length() > 0) {
                            double parsed = Double.parseDouble(cleanString);
                            NumberFormat formatter = NumberFormat.getCurrencyInstance();
                            DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) formatter).getDecimalFormatSymbols();
                            decimalFormatSymbols.setCurrencySymbol("");
                            ((DecimalFormat) formatter).setDecimalFormatSymbols(decimalFormatSymbols);
                            formatter.setMaximumFractionDigits(0);
                            current = formatter.format(parsed);
                        } else {
                            current = cleanString;
                        }
                        v.setText(current);
                        v.setSelection(current.length());
                        v.addTextChangedListener(this);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        public static void AddCurrencyTextWatcher(final EditText v, final Runnable AfterTextChanged) {
            v.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!s.toString().equals(current)) {
                        v.removeTextChangedListener(this);

                        String cleanString = ReplacePersianToDigit(s.toString());
                        if (cleanString.length() > 0) {
                            double parsed = Double.parseDouble(cleanString);
                            NumberFormat formatter = NumberFormat.getCurrencyInstance();
                            DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) formatter).getDecimalFormatSymbols();
                            decimalFormatSymbols.setCurrencySymbol("");
                            ((DecimalFormat) formatter).setDecimalFormatSymbols(decimalFormatSymbols);
                            formatter.setMaximumFractionDigits(0);
                            current = formatter.format(parsed);
                        } else {
                            current = cleanString;
                        }
                        v.setText(current);
                        v.setSelection(current.length());
                        v.addTextChangedListener(this);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    AfterTextChanged.run();
                }
            });
        }
    }

    public static class HandleApplication {
        public static void SetLocalPath(Context mContext) {
            PackageManager m = mContext.getPackageManager();
            String s = mContext.getPackageName();
            try {
                PackageInfo p = m.getPackageInfo(s, 0);
                LocalPath = p.applicationInfo.dataDir;
            } catch (PackageManager.NameNotFoundException ignored) {
            }
        }

        public static void verifyStoragePermissions(Activity activity) {
            int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        activity,
                        PERMISSIONS_STORAGE,
                        REQUEST_EXTERNAL_STORAGE
                );
            }
        }

        public static boolean isTextViewEllipsized(TextView textview) {
            boolean result = false;

            Layout layout = textview.getLayout();
            if (layout != null) {
                int lines = layout.getLineCount();
                if (lines > 0) {
                    int ellipsisCount = layout.getEllipsisCount(lines - 1);
                    if (ellipsisCount > 0) {
                        result = true;
                    }
                }
            }

            return result;
        }

        public static boolean isLanguageEn(String string) {
            String pattern = "^[A-Za-z0-9. ]+$";
            return string.matches(pattern);
        }

        public static void SavePreferences(Context mcContext, String key, String value) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mcContext);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            editor.apply();
        }

        public static String LoadPreferences(Context mcContext, String key) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mcContext);
            return preferences.getString(key, "");
        }

        public static String CreateRandomNumber() {
            Random r = new Random();
            int randnum = r.nextInt(99999 - 10000) + 10000;
            return String.valueOf(randnum);
        }

        public static String CreateRandomNumber(int randLenght) {
            Random r = new Random();
            int randnum = (int) (r.nextInt((int) ((Math.pow(10, randLenght + 1) - 1) - Math.pow(10, randLenght))) + Math.pow(10, randLenght));
            return String.valueOf(randnum);
        }

        public static void verifyPermissions(final Activity activity) {
            int permission = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int permission3 = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION);

            if (permission + permission3 != PackageManager.PERMISSION_GRANTED) {

                Snackbar.make(activity.findViewById(android.R.id.content),
                        "لطفااجازه دسترسی به حافظه و موقعیت را بدهید",
                        Snackbar.LENGTH_INDEFINITE).setAction("تایید دسترسی",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(activity,
                                        new String[]{android.Manifest.permission
                                                .WRITE_EXTERNAL_STORAGE,
                                                android.Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS);
                            }
                        }).show();
            }
        }

        public static boolean checkIfAlreadyhavePermission(final Activity activity) {
            int permission = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int permission3 = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION);

            return permission == PackageManager.PERMISSION_GRANTED
                    && permission3 == PackageManager.PERMISSION_GRANTED;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        public static Snackbar makeText(Activity activity, String message, int duration) {
            View layout;
            Snackbar snackbar = Snackbar
                    .make(activity.findViewById(android.R.id.content), message, duration);
            layout = snackbar.getView();
            layout.setBackgroundColor(activity.getResources().getColor(R.color.colorPrimary));
            android.widget.TextView text = layout.findViewById(R.id.snackbar_text);
            text.setGravity(Gravity.RIGHT);
            text.setTextColor(activity.getResources().getColor(R.color.icons));
            HandleViewAndFontSize.overrideFonts(activity, text);

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) text.getLayoutParams();
            params.setLayoutDirection(ViewGroup.LayoutParams.MATCH_PARENT);
            text.setLayoutParams(params);

            return snackbar;

        }

        public static void ToastMessage(Activity activity, String message) {
            View layout = LayoutInflater.from(activity).inflate(R.layout.toast,
                    (ViewGroup) activity.findViewById(R.id.toast_layout_root));

            TextView text = layout.findViewById(R.id.text);
            text.setText(message);

            Toast toast = new Toast(activity);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            HandleViewAndFontSize.overrideFonts(activity, layout);
            toast.show();
        }

        public static void ToastMessage(Activity activity, String message, int Lenght) {
            View layout = LayoutInflater.from(activity).inflate(R.layout.toast,
                    (ViewGroup) activity.findViewById(R.id.toast_layout_root));

            TextView text = layout.findViewById(R.id.text);
            text.setText(message);

            Toast toast = new Toast(activity);
            toast.setDuration(Lenght);
            toast.setView(layout);
            HandleViewAndFontSize.overrideFonts(activity, layout);
            toast.show();
        }

        public static void SnackMessage(Activity activity, String mMessage, String btnTitle, View.OnClickListener onClickListener) {
            View view = activity.findViewById(android.R.id.content);
            HandleViewAndFontSize.overrideFonts(activity, view);
            Snackbar.make(view, mMessage,
                    Snackbar.LENGTH_INDEFINITE).setAction(btnTitle, onClickListener).show();

        }

        public static void handleUncaughtException(Activity a, Thread thread, Throwable e) {
            e.printStackTrace();

            Intent intent = new Intent();
            intent.setAction("com.dpa_me.dpakit.SEND_LOG");
            intent.putExtra("Error", e.toString());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            a.startActivity(intent);

            System.exit(1);
        }

        public static void CloseProgressDialog() {
            try {
                if (mProgressDialog != null)
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
            } catch (Exception ex) {
                mProgressDialog.dismiss();
            }
        }

        public static void CloseSoftKeypad(Activity activity, View view) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        public static void OpenSoftKeypad(Activity activity, View view) {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInputFromWindow(
                    view.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        }

        public static void ScrollListViewToBottom(final ListView mListView) {
            mListView.post(new Runnable() {
                @Override
                public void run() {
                    mListView.setSelection(mListView.getAdapter().getCount() - 1);
                }
            });
        }

        public static void EmptyFragmentManager(Fragment a) {
            try {
                Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
                childFragmentManager.setAccessible(true);
                childFragmentManager.set(a, null);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        public static void handleGetApiError(Activity activity, View.OnClickListener onClickListener) {
            CloseProgressDialog();
            SnackMessage(activity, activity.getString(R.string.messOperationFailed),
                    activity.getString(R.string.lblRetry), onClickListener);
        }

        public static Context SetActivityParams(final Activity activity, int ActivityLayout,
                                                boolean HasDrawerLayout, String Title, boolean HideStatusBar) {
            Thread.setDefaultUncaughtExceptionHandler (new Thread.UncaughtExceptionHandler()
            {
                @Override
                public void uncaughtException (Thread thread, Throwable e)
                {
                    handleUncaughtException (activity, thread, e);
                }
            });

            activity.setContentView(ActivityLayout);
            activity.setTitle("");
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

            xdpi = metrics.widthPixels;
            ydpi = metrics.heightPixels;
            DisplayDensityDpi = metrics.density;

            if (HideStatusBar) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                }

                activity.getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            }

            ImageView BackBtn = activity.findViewById(R.id.BackBtn);
            if (BackBtn != null) {
                BackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.finish();
                    }
                });
            }

            TextView mTitle = activity.findViewById(R.id.Title);
            if (mTitle != null) {
                mTitle.setText(Title);
            }
            return activity;
        }

        public static View FindViewByName(Context context, String ViewName, View Parent) {
            int id = context.getResources().getIdentifier(ViewName, "id", context.getPackageName());
            return Parent.findViewById(id);
        }

        public static void ShowProgressDialog(Activity activity) {
            if (mProgressDialog == null)
                mProgressDialog = new ProgressDialog(activity, activity.getResources().getString(R.string.messPleaseWait));

            if (!mProgressDialog.isShowing()) {
                try {
                    mProgressDialog = new ProgressDialog(activity, activity.getResources().getString(R.string.messPleaseWait));
                    mProgressDialog.setCancelable(false);
                    mProgressDialog.show();
                } catch (Exception ignored) {

                }
            }
        }

        public static void ShowProgressDialog(Activity activity, String Message) {
            mProgressDialog = new ProgressDialog(activity, Message);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        public static int GetResourceID(Context mContext, String ResourceName, String ResType) {
            Resources resources = mContext.getResources();
            return resources.getIdentifier(ResourceName, ResType,
                    mContext.getPackageName());
        }

        public static String GetResourceName(Context mContext, int ResourceID) {
            return mContext.getResources().getResourceEntryName(ResourceID);
        }
    }

    public static class HandleViewAndFontSize {
        public static void overrideFonts(final Context context, final View v) {
            try {
                if (v instanceof ViewGroup) {
                    ViewGroup vg = (ViewGroup) v;
                    for (int i = 0; i < vg.getChildCount(); i++) {
                        View child = vg.getChildAt(i);
                        overrideFonts(context, child);
                    }
                } else if (v instanceof TextView) {
                    ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "BTrafcBd.ttf"));
                }
            } catch (Exception ignored) {
            }
        }

        public static void overrideFonts(final Context context, final View v, String fontName) {
            try {
                if (v instanceof ViewGroup) {
                    ViewGroup vg = (ViewGroup) v;
                    for (int i = 0; i < vg.getChildCount(); i++) {
                        View child = vg.getChildAt(i);
                        overrideFonts(context, child, fontName);
                    }
                } else if (v instanceof TextView) {
                    ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), fontName));
                }
            } catch (Exception ignored) {
            }
        }

        public static void replaceFont(String staticTypefaceFieldName,
                                       final Typeface newTypeface) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Map<String, Typeface> newMap = new HashMap<>();
                newMap.put("sans-serif", newTypeface);
                try {
                    final Field staticField = Typeface.class
                            .getDeclaredField("sSystemFontMap");
                    staticField.setAccessible(true);
                    staticField.set(null, newMap);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    final Field staticField = Typeface.class
                            .getDeclaredField(staticTypefaceFieldName);
                    staticField.setAccessible(true);
                    staticField.set(null, newTypeface);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class HandleDataBase {
        public static JSONArray fromJSON(String json) {
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return jsonArray;
        }

        public static String JSONStringCorrector(String JSON) {
            JSON = JSON.replace("\\\"", "\"");
            JSON = JSON.replace("\\r\\n", "");
            JSON = JSON.replace("\"[", "[");
            JSON = JSON.replace("]\"", "]");
            JSON = JSON.replace("\":\"{\"", "\":{\"");
            JSON = JSON.replace("}\",\"", "},\"");
            JSON = JSON.replace("\\/", "/");
            JSON = JSON.replace("}\"}", "}}");

            return JSON;
        }

        public static void ExportDB(Context mContext, String db_name) {
            HandleApplication.SetLocalPath(mContext);
            String currentDBPath = LocalPath + "/databases/" + db_name;
            String backupDBPath = Environment.getExternalStorageDirectory() + "/fitnessica.db3";

            File currentDB = new File(currentDBPath);
            File backupDB = new File(backupDBPath);
            try {
                FileChannel source = new FileInputStream(currentDB).getChannel();
                FileChannel destination = new FileOutputStream(backupDB).getChannel();
                destination.transferFrom(source, 0, source.size());
                source.close();
                destination.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void ImportDB(Context mContext, String db_name) {
            HandleApplication.SetLocalPath(mContext);
            String currentDBPath = LocalPath + "/databases/" + db_name;
            String backupDBPath = Environment.getExternalStorageDirectory() + "/fitnessica.db3";

            File currentDB = new File(currentDBPath);
            File backupDB = new File(backupDBPath);

            try {
                FileChannel source = new FileInputStream(backupDB).getChannel();
                FileChannel destination = new FileOutputStream(currentDB).getChannel();
                destination.transferFrom(source, 0, source.size());
                source.close();
                destination.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static Cursor ReadData(SQLiteDatabase database, String sqltxt) {
            Cursor cursor;
            try {
                cursor = database.rawQuery(sqltxt, null);
                cursor.moveToFirst();
            } catch (Exception ex) {
                cursor = null;
            }
            return cursor;
        }

        public static boolean InsertData(SQLiteDatabase database, String sqltxt) {
            try {
                database.execSQL(sqltxt);
                return true;
            } catch (Exception ex) {
                Log.e("SQLite INSERT ERROR", " <<" + sqltxt + ">> " + ex.toString());
                return false;
            }
        }

        public static boolean UpdateData(SQLiteDatabase database, String sqltxt) {
            try {
                database.execSQL(sqltxt);
                return true;
            } catch (Exception ex) {
                Log.d("SQLite_Update_Error", ex.toString());
                return false;
            }
        }

        public static String GetMaxValue(SQLiteDatabase database, String TableName, String FieldName) {
            Cursor cur = ReadData(database, "SELECT MAX(" + FieldName + ") FROM " + TableName);
            cur.moveToFirst();
            return cur.getString(0);
        }

        public static String GetMaxValue(SQLiteDatabase database, String TableName, String FieldName, String Condition) {
            Cursor cur = ReadData(database, "SELECT MAX(" + FieldName + ") FROM " + TableName + " WHERE " + Condition);
            return cur.getString(0);
        }

        public static boolean isCurEmpty(Cursor cur) {
            return !(cur.moveToFirst()) || cur.getCount() == 0;
        }
    }
}
