package com.challengers.of.call;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.RotatingCircularDotsLoader;
import com.challengers.of.call.Add_Money_Popup.FirstRequestJson;
import com.challengers.of.call.BannedData.GetBannedResponseJson;
import com.challengers.of.call.Banned_APP.bannedapp;
import com.challengers.of.call.Cocquiz.AnswerRequestJson;
import com.challengers.of.call.Cocquiz.AnswerResponseJson;
import com.challengers.of.call.Cocquiz.GetCocResponseJson;
import com.challengers.of.call.DeviceUtils.ljsflsj;
import com.challengers.of.call.FiFityAPI.FifityapiRequestJson;
import com.challengers.of.call.Profiledata.WithdrawFiResponseJson;
import com.challengers.of.call.Retrofit.ServiceGenerator;
import com.challengers.of.call.Retrofit.UserService;
import com.challengers.of.call.data.GetDataBannedApp;
import com.challengers.of.call.testing.SettingsPreferences;
import com.challengers.of.call.testing.StaticUtils;
import com.challengers.of.call.testing.Utils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.challengers.of.call.data.Getquestiondata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.decrypt;
import static com.challengers.of.call.DeviceUtils.Java_AES_Cipher.encrypt;

public class Activityquiz extends AppCompatActivity {
	public static boolean click = false;
	public static List<Getquestiondata> Questiondata;
	CountDownTimer cTimer = null;
	TextView tvtimer;
	JSONObject jasonObject;
	ProgressBar mProgressBar, mProgressBar1;
	JSONArray jsonArray = null;
	TextView txtquestion, txtoptiona, txtoptionb, txtoptionc, txtoptiond, txtquestionno, txtscore;
	CardView cardViewa,cardViewb,cardViewc,cardViewd;
	Getquestiondata getquestiondata;
	LinearLayout l1, l2;
	String Contestid = "", Answer = "", Rightanswer, Loginid;
	int count = 0, Point = 0, Finalamount = 0;
	LinearLayout layoutoption;
	int i = 0;
	CountDownTimer starttimer;
	long startTime = 0;
	String error;
	public CardView layout_A, layout_B, layout_C, layout_D;
	String resTxt = null;
	private Vibrator vibrator;
	String Questionid, Question, Optiona, Optionb, Optionc, Optiond, Answer1;
	private MediaPlayer weoolmediaplayer;
	public String Totalwallet;
	public int btnPosition = 0;
	public ImageView fifty_fifty;
	private ljsflsj good;
//	AsyncCallWSinsertanswer asyncCallWSinsertanswer;
//	AsyncCallWS task;
	private JSONArray resultArray;
	private String From = "", From1 = "", Animate = "";
	public String Totallife;
	public String TotalLifes;
	public int totallifes;
	public String checkfifty;
	String Count = "";
	private static bannedapp bannedapps;
	public Dialog progressbar;

	@Override
	protected void onResume() {
		super.onResume();

		bannedapps = new bannedapp(Activityquiz.this);
		bannedapps.bannedbig();
	}

	@Override
	protected void onPause() {
		super.onPause();

		bannedapps = new bannedapp(Activityquiz.this);
		bannedapps.bannedbig();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activityquiz);

		progressbar = new Dialog(this);

		bannedapps = new bannedapp(Activityquiz.this);
		bannedapps.bannedbig();

		fifty_fifty = (ImageView) findViewById(R.id.fifty_fifty);
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		weoolmediaplayer = MediaPlayer.create(Activityquiz.this, R.raw.click);

		final SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

		SharedPreferences shear = getSharedPreferences("fiftyss", Context.MODE_PRIVATE);
		checkfifty = shear.getString("fifty", null);

		Totallife = prefs.getString("Totallife", null);
		if (Totallife.equalsIgnoreCase("0")){
			fifty_fifty.setVisibility(View.GONE);
		}else if(checkfifty.equalsIgnoreCase("true")) {
			fifty_fifty.setVisibility(View.VISIBLE);
		}else if(checkfifty.equalsIgnoreCase("false")) {
			fifty_fifty.setVisibility(View.GONE);
		}

		layout_A = (CardView) findViewById(R.id.cardview);
		layout_B = (CardView) findViewById(R.id.cardview2);
		layout_C = (CardView) findViewById(R.id.cardview3);
		layout_D = (CardView) findViewById(R.id.cardview4);


		fifty_fifty.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				fifty_fifty.setVisibility(View.GONE);
				if (SettingsPreferences.getSoundEnableDisable(Activityquiz.this)) {
					StaticUtils.backSoundonclick(Activityquiz.this);
				}
				if (SettingsPreferences.getVibration(Activityquiz.this)) {
					StaticUtils.vibrate(Activityquiz.this, StaticUtils.VIBRATION_DURATION);
				}
				Rightanswer = decrypt(good.key, getquestiondata.getAnswer());


				SharedPreferences shear = getSharedPreferences("fiftyss", Context.MODE_PRIVATE);
				checkfifty = shear.getString("fifty", null);

				totallifes = Integer.valueOf(decrypt(good.key, Totallife));

				if (checkfifty.equalsIgnoreCase("true")) {

					if (totallifes > 0) {
						checkfifty = "false";
						btnPosition = 0;


//                    Toast.makeText(Activityquiz.this, Rightanswer, Toast.LENGTH_SHORT).show();

						if ("A".equalsIgnoreCase(Rightanswer)) {
							btnPosition = 1;
						}
						if ("B".equalsIgnoreCase(Rightanswer)) {
							btnPosition = 2;
						}
						if ("C".equalsIgnoreCase(Rightanswer)) {
							btnPosition = 3;
						}
						if ("D".equalsIgnoreCase(Rightanswer)) {
							btnPosition = 4;
						}
						if (btnPosition == 1) {
//                            layout_B.setVisibility(View.GONE);
							txtoptionb.setText("");
							txtoptionc.setText("");
//                            layout_C.setVisibility(View.GONE);

						} else if (btnPosition == 2) {
//                            layout_C.setVisibility(View.GONE);
//                            layout_D.setVisibility(View.GONE);
							txtoptionc.setText("");
							txtoptiond.setText("");

						} else if (btnPosition == 3) {
//                            layout_D.setVisibility(View.GONE);
//                            layout_A.setVisibility(View.GONE);
							txtoptiond.setText("");
							txtoptiona.setText("");

						} else if (btnPosition == 4) {
//                            layout_A.setVisibility(View.GONE);
//                            layout_B.setVisibility(View.GONE);
							txtoptiona.setText("");
							txtoptionb.setText("");

						}

						SharedPreferences.Editor edit = shear.edit();
						edit.putString("fifty", "fales");
						edit.apply();

						totallifes--;
						TotalLifes = String.valueOf(totallifes);
						TotalLifes = encrypt(good.key, good.initVector, TotalLifes);
						SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
						SharedPreferences.Editor editor1 = pref2.edit();
						editor1.putString("Totallife", TotalLifes);
						editor1.apply();
						SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = prefs.edit();
						editor.putString("Totallife", TotalLifes);
						editor.apply();



					} else {
						Toast.makeText(Activityquiz.this, "you have a 0 life !", Toast.LENGTH_SHORT).show();
					}
				}else {
					Toast.makeText(Activityquiz.this, "Life Line Already Used !", Toast.LENGTH_SHORT).show();
				}
			}

		});


		Loginid = prefs.getString("Loginid", null);
		String Name = prefs.getString("Name", null);
		Contestid = getIntent().getStringExtra("Contestid");
		From = getIntent().getStringExtra("From");
		From1 = getIntent().getStringExtra("From1");

		l1 = findViewById(R.id.l1);
		l2 = findViewById(R.id.l2);

		Animate = getIntent().getStringExtra("Animate");
		Finalamount = getIntent().getIntExtra("Finalamount", 0);
		tvtimer = findViewById(R.id.tvTimer);
		count = getIntent().getIntExtra("count", 0);
		resultArray = new JSONArray();
		mProgressBar = (ProgressBar) findViewById(R.id.progressbar_timerview);
		mProgressBar1 = (ProgressBar) findViewById(R.id.progressbar1_timerview);
		txtquestion = findViewById(R.id.txtquestion);
		txtoptiona = (TextView) findViewById(R.id.optiona);
		txtoptionb = (TextView) findViewById(R.id.optionb);
		txtoptionc = (TextView) findViewById(R.id.optionc);
		txtoptiond = (TextView) findViewById(R.id.optiond);
		txtscore = findViewById(R.id.txtscore);
		txtscore.setText(Finalamount + "/100");
		txtquestionno = findViewById(R.id.questionno);
		layoutoption = findViewById(R.id.layoutoption);

		if (From1.equalsIgnoreCase("Contest") || From1.equalsIgnoreCase("Challenge")) {
//           Toast.makeText(Activityquiz.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
			l2.setVisibility(View.VISIBLE);
			l1.setVisibility(View.GONE);
			playtimer();

		} else {
			l2.setVisibility(View.GONE);
			l1.setVisibility(View.VISIBLE);
			if (Animate != null) {
				String data = getIntent().getStringExtra("data");
				try {
					resultArray = new JSONArray(data);
				} catch (JSONException e) {
					e.printStackTrace();
				}

				if (count < Questiondata.size()) {
					Nextquestion();
				}
			} else {
//                SendDataToServer(Loginid);
//				task = new AsyncCallWS();
//				task.execute();


				progressbar.setContentView(R.layout.progresbarlayout);
				progressbar.setCancelable(false);
				RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);

				RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Activityquiz.this,
						20, 60, ContextCompat.getColor(Activityquiz.this, R.color.white));
				loader.setAnimDuration(3000);

				rotatingCircularDotsLoader.addView(loader);

				progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
				progressbar.show();


				StartingTimeAPI();
			}
		}
		txtoptiona.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				fifty_fifty.setVisibility(View.GONE);
				cancelTimer();
				if (click == false) {
					view.startAnimation(AnimationUtils.loadAnimation(Activityquiz.this, R.anim.imageanim));
					if (count <= Questiondata.size()) {
						startTime = 0;
						startTime = System.currentTimeMillis();
						Answer = "A";
						Rightanswer = decrypt(good.key, getquestiondata.getAnswer());
						if (Answer.equalsIgnoreCase(Rightanswer)) {
							Point = 10;
							Point = Point + Integer.valueOf(tvtimer.getText().toString().trim());
							Finalamount = Finalamount + Point;
//                            layout_A.setCardBackgroundColor(Color.parseColor("#008000"));
							txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
							txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
							txtscore.setText(Finalamount + "/100");
							playsound(R.raw.rightanswer);
						} else {
							// Wrong answer color #730a07
							Point = 0;
							//txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
							txtoptiona.setBackgroundColor(Color.parseColor("#730a07"));
//                            layout_A.setCardBackgroundColor(Color.parseColor("#730a07"));
							txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
							Finalamount = Finalamount + Point;
							txtscore.setText(Finalamount + "/100");
							if (Rightanswer.equalsIgnoreCase("A")) {
								txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
								txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
							} else if (Rightanswer.equalsIgnoreCase("B")) {
								txtoptionb.setBackgroundColor(Color.parseColor("#008000"));
								txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
							} else if (Rightanswer.equalsIgnoreCase("C")) {

								txtoptionc.setBackgroundColor(Color.parseColor("#008000"));
								txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
							} else if (Rightanswer.equalsIgnoreCase("D")) {
								txtoptiond.setBackgroundColor(Color.parseColor("#008000"));
								txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
							}
							playsound(R.raw.wronganswer);
						}
						click = true;
						if (Networkcheck.isNetworkAvaliable(Activityquiz.this)) {
//							asyncCallWSinsertanswer = new AsyncCallWSinsertanswer();
//							asyncCallWSinsertanswer.execute();






							AnswerTimeAPI();
						} else {
						}
					}
				}
			}
		});
		txtoptionb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				fifty_fifty.setVisibility(View.GONE);
				cancelTimer();
				if (click == false) {
					view.startAnimation(AnimationUtils.loadAnimation(Activityquiz.this, R.anim.imageanim));
					if (count <= Questiondata.size()) {
						startTime = 0;
						startTime = System.currentTimeMillis();
						Answer = "B";
						Rightanswer = decrypt(good.key, getquestiondata.getAnswer());
						if (Answer.equalsIgnoreCase(Rightanswer)) {
							Point = 10;
							Point = Point + Integer.valueOf(tvtimer.getText().toString().trim());
							txtoptionb.setBackgroundColor(Color.parseColor("#008000"));
							txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
							Finalamount = Finalamount + Point;
							txtscore.setText(Finalamount + "/100");
							playsound(R.raw.rightanswer);
						} else {
							Point = 0;
							txtoptionb.setBackgroundColor(Color.parseColor("#730a07"));
							txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
							Finalamount = Finalamount + Point;
							txtscore.setText(Finalamount + "/100");
							if (Rightanswer.equalsIgnoreCase("A")) {
								txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
								txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
							} else if (Rightanswer.equalsIgnoreCase("B")) {
								txtoptionb.setBackgroundColor(Color.parseColor("#008000"));
								txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
							} else if (Rightanswer.equalsIgnoreCase("C")) {
								txtoptionc.setBackgroundColor(Color.parseColor("#008000"));
								txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
							} else if (Rightanswer.equalsIgnoreCase("D")) {
								txtoptiond.setBackgroundColor(Color.parseColor("#008000"));
								txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
							}
							playsound(R.raw.wronganswer);
						}
//						asyncCallWSinsertanswer = new AsyncCallWSinsertanswer();
//						asyncCallWSinsertanswer.execute();
						click = true;








						AnswerTimeAPI();
					}
				}
			}
		});
		txtoptionc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				fifty_fifty.setVisibility(View.GONE);
				cancelTimer();
				if (click == false) {
					view.startAnimation(AnimationUtils.loadAnimation(Activityquiz.this, R.anim.imageanim));
					if (count <= Questiondata.size()) {
						startTime = 0;
						startTime = System.currentTimeMillis();
						Answer = "C";
						Rightanswer = decrypt(good.key, getquestiondata.getAnswer());
						if (Answer.equalsIgnoreCase(Rightanswer)) {
							Point = 10;
							Point = Point + Integer.valueOf(tvtimer.getText().toString().trim());
							txtoptionc.setBackgroundColor(Color.parseColor("#008000"));
							txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
							Finalamount = Finalamount + Point;
							txtscore.setText(Finalamount + "/100");
							playsound(R.raw.rightanswer);
						} else {
							Point = 0;
							txtoptionc.setBackgroundColor(Color.parseColor("#730a07"));
							txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
							Finalamount = Finalamount + Point;
							txtscore.setText(Finalamount + "/100");
							if (Rightanswer.equalsIgnoreCase("A")) {
								txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
								txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
							} else if (Rightanswer.equalsIgnoreCase("B")) {
								txtoptionb.setBackgroundColor(Color.parseColor("#008000"));
								txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
							} else if (Rightanswer.equalsIgnoreCase("C")) {
								txtoptionc.setBackgroundColor(Color.parseColor("#008000"));
								txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
							} else if (Rightanswer.equalsIgnoreCase("D")) {
								txtoptiond.setBackgroundColor(Color.parseColor("#008000"));
								txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
							}
							playsound(R.raw.wronganswer);
						}
						click = true;
//						asyncCallWSinsertanswer = new AsyncCallWSinsertanswer();
//						asyncCallWSinsertanswer.execute();




						AnswerTimeAPI();
					}
				}
			}
		});
		txtoptiond.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				fifty_fifty.setVisibility(View.GONE);
				cancelTimer();
				if (click == false) {
					view.startAnimation(AnimationUtils.loadAnimation(Activityquiz.this, R.anim.imageanim));
					if (count <= Questiondata.size()) {
						startTime = 0;
						startTime = System.currentTimeMillis();
						Answer = "D";
						Rightanswer = decrypt(good.key, getquestiondata.getAnswer());
						if (Answer.equalsIgnoreCase(Rightanswer)) {
							Point = 10;
							Point = Point + Integer.valueOf(tvtimer.getText().toString().trim());
							txtoptiond.setBackgroundColor(Color.parseColor("#008000"));
							txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
							Finalamount = Finalamount + Point;
							txtscore.setText(Finalamount + "/100");
							playsound(R.raw.rightanswer);
						} else {
							Point = 0;
							txtoptiond.setBackgroundColor(Color.parseColor("#730a07"));
							txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
							Finalamount = Finalamount + Point;
							txtscore.setText(Finalamount + "/100");
							//highlighting right answer
							if (Rightanswer.equalsIgnoreCase("A")) {
								txtoptiona.setBackgroundColor(Color.parseColor("#008000"));
								txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
							} else if (Rightanswer.equalsIgnoreCase("B")) {
								txtoptionb.setBackgroundColor(Color.parseColor("#008000"));
								txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
							} else if (Rightanswer.equalsIgnoreCase("C")) {
								txtoptionc.setBackgroundColor(Color.parseColor("#008000"));
								txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
							} else if (Rightanswer.equalsIgnoreCase("D")) {
								txtoptiond.setBackgroundColor(Color.parseColor("#008000"));
								txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
							}
							playsound(R.raw.wronganswer);
						}
						click = true;
//						asyncCallWSinsertanswer = new AsyncCallWSinsertanswer();
//						asyncCallWSinsertanswer.execute();




						AnswerTimeAPI();
					}
				}
			}
		});
	}


	void startTimer() {
		cTimer = new CountDownTimer(11000, 1) {
			public void onTick(long millisUntilFinished) {
				mProgressBar1.setProgress((int) (millisUntilFinished));
				tvtimer.setText(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) + "");
			}

			public void onFinish() {
				mProgressBar1.setVisibility(View.INVISIBLE);
				mProgressBar1.setVisibility(View.VISIBLE);
				click = true;
				Answer = "N/A";
				Rightanswer = decrypt(good.key, getquestiondata.getAnswer());
				Point = 0;
//				AsyncCallWSinsertanswer task = new AsyncCallWSinsertanswer();
//				task.execute();



				AnswerTimeAPI();
			}
		};
		cTimer.start();
	}



	void cancelTimer() {
		if (cTimer != null)
			cTimer.cancel();
	}

	private void Nextquestion() {
		mProgressBar.setVisibility(View.INVISIBLE);
		mProgressBar1.setVisibility(View.VISIBLE);
		mProgressBar1.setMax(10 * 1000);
		if (count < Questiondata.size()) {
//            txtoptiona.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            txtoptionb.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            txtoptionc.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            txtoptiond.setBackgroundColor(Color.parseColor("#FFFFFF"));
			txtoptiona.setTextColor(Color.parseColor("#FFFFFF"));
			txtoptionb.setTextColor(Color.parseColor("#FFFFFF"));
			txtoptionc.setTextColor(Color.parseColor("#FFFFFF"));
			txtoptiond.setTextColor(Color.parseColor("#FFFFFF"));
			getquestiondata = Questiondata.get(count);
			txtquestion.setText(decrypt(good.key, getquestiondata.getQuestion()));
			txtoptiona.setText(decrypt(good.key, getquestiondata.getOptiona()));
			txtoptionb.setText(decrypt(good.key, getquestiondata.getOptionb()));
			txtoptionc.setText(decrypt(good.key, getquestiondata.getOptionc()));
			txtoptiond.setText(decrypt(good.key, getquestiondata.getOptiond()));
			txtquestionno.setText((count + 1) + "/" + Questiondata.size());
			startTimer();
			count++;
		} else {
			cancelTimer();
			count++;
		}
		click = false;
	}

	@Override
	public void onBackPressed() {
//        finish();
//        super.onBackPressed();
	}


	private void playsound(int sound) {
		MediaPlayer mediaPlayer = MediaPlayer.create(Activityquiz.this, sound);
		mediaPlayer.start();
		mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mediaPlayer) {
				mediaPlayer.release();
			}
		});
	}

	private void animate() {
		if (count < Questiondata.size()) {
			long difference = System.currentTimeMillis() - startTime;
			if (difference < 20000) {
				AsyncCallWSwait taskwait = new AsyncCallWSwait();
				taskwait.execute();
			} else {
				AsyncCallWSwait taskwait = new AsyncCallWSwait();
				taskwait.execute();
			}
		}
	}

	public boolean isApplicationSentToBackground(final Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
		if (!tasks.isEmpty()) {
			ComponentName topActivity = tasks.get(0).topActivity;
			if (!topActivity.getPackageName().equals(context.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void onStop() {
		super.onStop();
	}


	private void setFadeAnimation(View view) {
		AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
		anim.setDuration(200);
		view.startAnimation(anim);
	}

	private void playtimer() {
		l1 = findViewById(R.id.l1);
		l2 = findViewById(R.id.l2);
		final TextView textView = findViewById(R.id.textView);
		l1.setVisibility(View.GONE);
		l2.setVisibility(View.VISIBLE);
		starttimer = new CountDownTimer(6000, 1000) {
			public void onTick(long millisUntilFinished) {
				playsound(R.raw.timersound);
				String second = (millisUntilFinished / 1000) + "";
				textView.setText(second);
				textView.setTextSize(150);
			}

			public void onFinish() {


				playsound(R.raw.playsound);
				textView.setText("P L A Y");
				textView.setTextSize(90);


				CountDownTimer t = new CountDownTimer(2000, 1000) {
					@Override
					public void onTick(long l) {

					}

					@Override
					public void onFinish() {

//                        SendDataToServer(Loginid);
//						task = new AsyncCallWS();
//						task.execute();
						progressbar.setContentView(R.layout.progresbarlayout);
						progressbar.setCancelable(false);
						RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);

						RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Activityquiz.this,
								20, 60, ContextCompat.getColor(Activityquiz.this, R.color.white));
						loader.setAnimDuration(3000);

						rotatingCircularDotsLoader.addView(loader);

						progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
						progressbar.show();
						
						StartingTimeAPI();
					}
				};
				t.start();
			}
		}.start();
	}

	private void StartingTimeAPI() {
		SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

		String Password = prefs.getString("Password", null);
		String Usernames = prefs.getString("Name", null);
		String Loginid = prefs.getString("Loginid", null);

		UserService userService = ServiceGenerator.createService(UserService.class,  decrypt(good.key, Usernames), decrypt(good.key, Password));
		userService.getquizone().enqueue(new Callback<GetCocResponseJson>() {
			@Override
			public void onResponse(Call<GetCocResponseJson> call, Response<GetCocResponseJson> response) {
				if (response.isSuccessful()) {
					Questiondata = new ArrayList<>();
					for (int z=0; z<response.body().data.size(); z++){



						Questionid = response.body().data.get(z).Questionid;
						Question = response.body().data.get(z).Question;
						Optiona = response.body().data.get(z).Optiona;
						Optionb = response.body().data.get(z).Optionb;
						Optionc = response.body().data.get(z).Optionc;
						Optiond = response.body().data.get(z).Optiond;
						Answer1 = response.body().data.get(z).Answer;


						Questionid = encrypt(good.key, good.initVector, Questionid);
						Question = encrypt(good.key, good.initVector, Question);
						Optiona = encrypt(good.key, good.initVector, Optiona);
						Optionb = encrypt(good.key, good.initVector, Optionb);
						Optionc = encrypt(good.key, good.initVector, Optionc);
						Optiond = encrypt(good.key, good.initVector, Optiond);
						Answer1 = encrypt(good.key, good.initVector, Answer1);
						Getquestiondata getquestiondata1 = new Getquestiondata();
						getquestiondata1.setQuestionid(Questionid);
						getquestiondata1.setQuestion(Question);
						getquestiondata1.setOptiona(Optiona);
						getquestiondata1.setOptionb(Optionb);
						getquestiondata1.setOptionc(Optionc);
						getquestiondata1.setOptiond(Optiond);
						getquestiondata1.setAnswer(Answer1);
						Questiondata.add(getquestiondata1);
					}

					if (response.body().data.size() > 0) {
						l1.setVisibility(View.VISIBLE);
						l2.setVisibility(View.GONE);
						Nextquestion();
					} else {
						Toast.makeText(Activityquiz.this, "Please try again", Toast.LENGTH_LONG).show();
					}

				} else {
					switch (response.code()) {
						case 401:
							Toast.makeText(Activityquiz.this, "email and pass not check", Toast.LENGTH_SHORT).show();
							break;
						case 404:
							Toast.makeText(Activityquiz.this, "not found", Toast.LENGTH_SHORT).show();
							break;
						case 500:
							Toast.makeText(Activityquiz.this, "server broken", Toast.LENGTH_SHORT).show();
							break;
						default:
							Toast.makeText(Activityquiz.this, "unknown error", Toast.LENGTH_SHORT).show();
							break;
					}

				}
				progressbar.dismiss();
			}

			@Override
			public void onFailure(Call<GetCocResponseJson> call, Throwable t) {
				Log.e("System error:", t.getLocalizedMessage());
			}
		});
	}


	/////// Starting time api
//	public class AsyncCallWS extends AsyncTask<String, Void, Void> {
//		String displayText;
//		String Questionid, Question, Optiona, Optionb, Optionc, Optiond, Answer1;
//		private KProgressHUD hud;
//
//		@Override
//		protected Void doInBackground(String... params) {
//			displayText = WebService.Selectquestion("AllQuestion");
//			Questiondata = new ArrayList<>();
//			if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//				try {
//					jasonObject = new JSONObject(displayText);
//					jsonArray = jasonObject.getJSONArray("Response");
//					for (int i = 0; i < jsonArray.length(); i++) {
//						Getquestiondata getquestiondata1 = new Getquestiondata();
//						JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//						Questionid = jsonrowdata.getString("Questionid");
//						Question = jsonrowdata.getString("Question");
//						Optiona = jsonrowdata.getString("Optiona");
//						Optionb = jsonrowdata.getString("Optionb");
//						Optionc = jsonrowdata.getString("Optionc");
//						Optiond = jsonrowdata.getString("Optiond");
//						Answer1 = jsonrowdata.getString("Answer");
//
//						Questionid = encrypt(good.key, good.initVector, Questionid);
//						Question = encrypt(good.key, good.initVector, Question);
//						Optiona = encrypt(good.key, good.initVector, Optiona);
//						Optionb = encrypt(good.key, good.initVector, Optionb);
//						Optionc = encrypt(good.key, good.initVector, Optionc);
//						Optiond = encrypt(good.key, good.initVector, Optiond);
//						Answer1 = encrypt(good.key, good.initVector, Answer1);
//
//						getquestiondata1.setQuestionid(Questionid);
//						getquestiondata1.setQuestion(Question);
//						getquestiondata1.setOptiona(Optiona);
//						getquestiondata1.setOptionb(Optionb);
//						getquestiondata1.setOptionc(Optionc);
//						getquestiondata1.setOptiond(Optiond);
//						getquestiondata1.setAnswer(Answer1);
//						Questiondata.add(getquestiondata1);
//					}
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//			}
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(Void result) {
//
//			progressbar.dismiss();
//
//			if (displayText.equalsIgnoreCase("connection fault")) {
//				l1.setVisibility(View.GONE);
//				Toast.makeText(Activityquiz.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//			} else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//				l1.setVisibility(View.GONE);
//				Toast.makeText(Activityquiz.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//			}
////            else if (jsonArray !=null)
////            {
//			if (jsonArray.length() > 0) {
//				l1.setVisibility(View.VISIBLE);
//				l2.setVisibility(View.GONE);
//				Nextquestion();
//			} else {
//				Toast.makeText(Activityquiz.this, "Please try again", Toast.LENGTH_LONG).show();
//			}
////            }
////            else{
////                Toast.makeText(Activityquiz.this, "Oops it's internet error please go to back then restart game ", Toast.LENGTH_LONG).show();
////
////
////
////
//////                AsyncCallReturnMoney task = new AsyncCallReturnMoney();
//////                task.execute();
////            }
//		}
//
//		@Override
//		protected void onPreExecute() {
//			if (!isApplicationSentToBackground(Activityquiz.this)) {
//				progressbar.setContentView(R.layout.progresbarlayout);
//				progressbar.setCancelable(false);
//				RotatingCircularDotsLoader rotatingCircularDotsLoader = (RotatingCircularDotsLoader)progressbar.findViewById(R.id.progress);
//
//				RotatingCircularDotsLoader loader = new RotatingCircularDotsLoader(Activityquiz.this,
//						20, 60, ContextCompat.getColor(Activityquiz.this, R.color.white));
//				loader.setAnimDuration(3000);
//
//				rotatingCircularDotsLoader.addView(loader);
//
//				progressbar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // popup background transparent
//				progressbar.show();
//			}
//			l1.setVisibility(View.GONE);
//		}
//
//		@Override
//		protected void onProgressUpdate(Void... values) {
//		}
//	}

	private void AnswerTimeAPI() {

		getquestiondata = Questiondata.get(count - 1);
		Questionid = getquestiondata.getQuestionid();


		SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);

		String Password = prefs.getString("Password", null);
		String Usernames = prefs.getString("Name", null);
		String Loginids = prefs.getString("Loginid", null);
		String TotalFifty = prefs.getString("Totallife", null);
		final AnswerRequestJson request = new AnswerRequestJson();
		int counter = count;
		int size = Questiondata.size();
		request.setLoginid(decrypt(good.key, Loginids));
		request.setContestid(decrypt(good.key, Contestid));
		request.setQuestionid(decrypt(good.key, Questionid));
		request.setRightanswer(Rightanswer);
		request.setAnswer(Answer);
		request.setPoint(String.valueOf(Point));
		request.setCounter(String.valueOf(counter));
		request.setSize(String.valueOf(size));
		request.setTotalLifes(decrypt(good.key, TotalFifty));




		UserService service = ServiceGenerator.createService(UserService.class, decrypt(good.key, Usernames), decrypt(good.key, Password));
		service.answerquit(request).enqueue(new Callback<AnswerResponseJson>() {
			@Override
			public void onResponse(Call<AnswerResponseJson> call, Response<AnswerResponseJson> response) {
				if (Utils.isNetworkAvailable(Activityquiz.this)) {

					if (response.isSuccessful()) {


						Count = response.body().Count;


						if (count == Questiondata.size()) {
							boolean check = isApplicationSentToBackground(Activityquiz.this);
							if (check == false) {

								try {
									// checking valid integer using parseInt() method
									Integer.parseInt(Count);
								} catch (NumberFormatException e) {

								}
								if (Count != null && Count != "") {
									String Counts = encrypt(good.key, good.initVector, Count);
									SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
									SharedPreferences.Editor editor1 = pref2.edit();
									editor1.putString("Totalwallet", Counts);
//                            Toast.makeText(Activityquiz.this, "totallMoney", Toast.LENGTH_SHORT).show();

									editor1.apply();
								}
								Intent intent = new Intent(Activityquiz.this, Questionwiseresult.class);
								intent.putExtra("Loginid", Loginid);
								intent.putExtra("Contestid", Contestid);
								intent.putExtra("From", From);

								startActivity(intent);
								finish();
							} else {
								finish();
							}
						} else if (count < Questiondata.size()) {
							if (Count != null && Count != "") {
								String Counts = encrypt(good.key, good.initVector, Count);
								SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
								SharedPreferences.Editor editor1 = pref2.edit();
								editor1.putString("Totalwallet", Counts);
//                        Toast.makeText(Activityquiz.this, "totallMoney", Toast.LENGTH_SHORT).show();

								editor1.apply();
							}


							if (Answer.equalsIgnoreCase("N/A")) {
								Nextquestion();
							} else {
								animate();
							}
						}



					} else {
						switch (response.code()) {
							case 401:
								Toast.makeText(Activityquiz.this, "email and pass not check", Toast.LENGTH_SHORT).show();
								break;
							case 403:
								Toast.makeText(Activityquiz.this, "ForbiddenException", Toast.LENGTH_SHORT).show();
								break;
							case 404:
								Toast.makeText(Activityquiz.this, "not found", Toast.LENGTH_SHORT).show();
								break;
							case 500:
								Toast.makeText(Activityquiz.this, "server broken", Toast.LENGTH_SHORT).show();
								break;
							default:
								Toast.makeText(Activityquiz.this, "unknown error", Toast.LENGTH_SHORT).show();
								break;

						}

					}

				} else {
					Toast.makeText(Activityquiz.this, "No internet connection!", Toast.LENGTH_LONG).show();

					if (count == Questiondata.size()) {
						boolean check = isApplicationSentToBackground(Activityquiz.this);
						if (check == false) {


							try {
								Integer.parseInt(Count);
							} catch (NumberFormatException e) {
							}
							if (Count != null && Count != "") {
								SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
								SharedPreferences.Editor editor1 = pref2.edit();
								editor1.putString("Totalwallet", Count);


								editor1.apply();
							}
							Intent intent = new Intent(Activityquiz.this, Questionwiseresult.class);
							intent.putExtra("Loginid", Loginid);
							intent.putExtra("Contestid", Contestid);
							intent.putExtra("From", From);

							startActivity(intent);
							finish();
						} else {
							Nextquestion();
						}
//                    else {
//                        finish();
//                    }
					} else if (count < Questiondata.size()) {
						if (Answer.equalsIgnoreCase("N/A")) {
							Nextquestion();
						} else {
							animate();
						}
					}

				}




			}

			@Override
			public void onFailure(Call<AnswerResponseJson> call, Throwable t) {
				t.printStackTrace();

				Toast.makeText(Activityquiz.this, "Please try after some times...", Toast.LENGTH_LONG).show();
				if (count == Questiondata.size()) {
					boolean check = isApplicationSentToBackground(Activityquiz.this);
					if (check == false) {

						try {
							// checking valid integer using parseInt() method
							Integer.parseInt(Count);
						} catch (NumberFormatException e) {

						}
						if (Count != null && Count != "") {
							String Counts = encrypt(good.key, good.initVector, Count);
							SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
							SharedPreferences.Editor editor1 = pref2.edit();

							editor1.putString("Totalwallet", Counts);
							editor1.apply();

						}
						Intent intent = new Intent(Activityquiz.this, Questionwiseresult.class);
						intent.putExtra("Loginid", Loginid);
						intent.putExtra("Contestid", Contestid);
						intent.putExtra("From", From);

						startActivity(intent);
						finish();
					}
//                    else {
//                        finish();
//                    }
				} else if (count < Questiondata.size()) {
					if (Answer.equalsIgnoreCase("N/A")) {
						Nextquestion();
					} else {
						animate();
					}
				}
				Log.e("System error:", t.getLocalizedMessage());

			}
		});

	}
	/////////// Answer time api
//	public class AsyncCallWSinsertanswer extends AsyncTask<String, Void, Void> {
//		String displayText = "";
//		String Questionid, Count = "";
////        private KProgressHUD hud;
//
//
//		@Override
//		protected Void doInBackground(String... params) {
//
//			SharedPreferences prefs = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//			String TotalFifty = prefs.getString("Totallife", null);
//			int counter = count;
//			int size = Questiondata.size();

//
//
//			Rightanswer = encrypt(good.key, good.initVector, Rightanswer);
//			Answer = encrypt(good.key, good.initVector, Answer);
//			String Points = encrypt(good.key, good.initVector, String.valueOf(Point));
//			String counters = encrypt(good.key, good.initVector, String.valueOf(counter));
//			String sizes = encrypt(good.key, good.initVector, String.valueOf(size));
//
//

//
//
//			displayText = WebService.Insertanswer(Loginid, Contestid, Questionid, Rightanswer, Answer, Points, counters, sizes,TotalFifty, "QueResponse");
//			if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//
//				try {
//					jasonObject = new JSONObject(displayText);
//					jsonArray = jasonObject.getJSONArray("Response");
//					for (int i = 0; i < jsonArray.length(); i++) {
//						JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//						Count = jsonrowdata.getString("Count");
//
//					}
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//			}
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(Void result) {
////             progressbar.dismiss();
//			if (displayText.equalsIgnoreCase("connection fault")) {
//				Toast.makeText(Activityquiz.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
////                startActivity(new Intent(Activityquiz.this,Pop.class));
//
//
//				if (count == Questiondata.size()) {
//					boolean check = isApplicationSentToBackground(Activityquiz.this);
//					if (check == false) {
//
//
//						try {
//							Integer.parseInt(Count);
//						} catch (NumberFormatException e) {
//						}
//						if (Count != null && Count != "") {
//							SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//							SharedPreferences.Editor editor1 = pref2.edit();
//							editor1.putString("Totalwallet", Count);
//
//
//							editor1.apply();
//						}
//						Intent intent = new Intent(Activityquiz.this, Questionwiseresult.class);
//						intent.putExtra("Loginid", Loginid);
//						intent.putExtra("Contestid", Contestid);
//						intent.putExtra("From", From);
//
//						startActivity(intent);
//						finish();
//					} else {
//						Nextquestion();
//					}
////                    else {
////                        finish();
////                    }
//				} else if (count < Questiondata.size()) {
//					if (Answer.equalsIgnoreCase("N/A")) {
//						Nextquestion();
//					} else {
//						animate();
//					}
//				}
//
//			} else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//
//				Toast.makeText(Activityquiz.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//				if (count == Questiondata.size()) {
//					boolean check = isApplicationSentToBackground(Activityquiz.this);
//					if (check == false) {
//
//						try {
//							// checking valid integer using parseInt() method
//							Integer.parseInt(Count);
//						} catch (NumberFormatException e) {
//
//						}
//						if (Count != null && Count != "") {
//							String Counts = encrypt(good.key, good.initVector, Count);
//							SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//							SharedPreferences.Editor editor1 = pref2.edit();
//
//							editor1.putString("Totalwallet", Counts);
//							editor1.apply();
//
//						}
//						Intent intent = new Intent(Activityquiz.this, Questionwiseresult.class);
//						intent.putExtra("Loginid", Loginid);
//						intent.putExtra("Contestid", Contestid);
//						intent.putExtra("From", From);
//
//						startActivity(intent);
//						finish();
//					}
////                    else {
////                        finish();
////                    }
//				} else if (count < Questiondata.size()) {
//					if (Answer.equalsIgnoreCase("N/A")) {
//						Nextquestion();
//					} else {
//						animate();
//					}
//				}
//
//			} else {
//				if (count == Questiondata.size()) {
//					boolean check = isApplicationSentToBackground(Activityquiz.this);
//					if (check == false) {
//
//						try {
//							// checking valid integer using parseInt() method
//							Integer.parseInt(Count);
//						} catch (NumberFormatException e) {
//
//						}
//						if (Count != null && Count != "") {
//							String Counts = encrypt(good.key, good.initVector, Count);
//							SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//							SharedPreferences.Editor editor1 = pref2.edit();
//							editor1.putString("Totalwallet", Counts);
////                            Toast.makeText(Activityquiz.this, "totallMoney", Toast.LENGTH_SHORT).show();
//
//							editor1.apply();
//						}
//						Intent intent = new Intent(Activityquiz.this, Questionwiseresult.class);
//						intent.putExtra("Loginid", Loginid);
//						intent.putExtra("Contestid", Contestid);
//						intent.putExtra("From", From);
//
//						startActivity(intent);
//						finish();
//					} else {
//						finish();
//					}
//				} else if (count < Questiondata.size()) {
//					if (Count != null && Count != "") {
//						String Counts = encrypt(good.key, good.initVector, Count);
//						SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//						SharedPreferences.Editor editor1 = pref2.edit();
//						editor1.putString("Totalwallet", Counts);
////                        Toast.makeText(Activityquiz.this, "totallMoney", Toast.LENGTH_SHORT).show();
//
//						editor1.apply();
//					}
//
//
//					if (Answer.equalsIgnoreCase("N/A")) {
//						Nextquestion();
//					} else {
//						animate();
//					}
//				}
//			}
//		}
//
//		@Override
//		protected void onPreExecute() {
//			getquestiondata = Questiondata.get(count - 1);
//			Questionid = getquestiondata.getQuestionid();
//		}
//
//		@Override
//		protected void onProgressUpdate(Void... values) {
//		}
//	}

	public class AsyncCallWSwait extends AsyncTask<String, Void, Void> {
		String displayText;
		private KProgressHUD hud;

		@Override
		protected Void doInBackground(String... params) {

			long difference = System.currentTimeMillis() - startTime;
			if (difference < 20000) {
				try {
					Thread.currentThread();
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {

			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Intent intent = new Intent(Activityquiz.this, Displayanimation.class);
			intent.putExtra("Answer", Answer);
			intent.putExtra("Rightanswer", Rightanswer);
			intent.putExtra("Contestid", Contestid);
			intent.putExtra("data", resultArray.toString());
			intent.putExtra("Finalamount", Finalamount);
			intent.putExtra("From", From);
			intent.putExtra("From1", From1);
			intent.putExtra("count", count);
			intent.putExtra("paid", "paid");
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}


	@Override
	protected void onRestart() {
		super.onRestart();
	}

//    public class AsyncCallReturnMoney extends AsyncTask<String, Void, Void> {
//        String displayText,refcode;
//        String Verify;
//        String Totalwallet;
//        private KProgressHUD hud;
//        @Override
//        protected Void doInBackground(String... params) {
//
//
//
//
//            displayText = WebService.ReturnMoney(Loginid,Contestid, "ReturnMoneyl");
//            if (displayText != "" && displayText != null && displayText != "connection fault" && !displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                try {
//                    jasonObject = new JSONObject(displayText);
//                    jsonArray = jasonObject.getJSONArray("Response");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonrowdata = jsonArray.getJSONObject(i);
//                        Totalwallet = jsonrowdata.getString("Totalwallet");
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            hud.dismiss();
//            if (displayText.equalsIgnoreCase("connection fault")) {
//                Toast.makeText(Activityquiz.this, "Please Check your internet connection", Toast.LENGTH_LONG).show();
//            } else if (displayText.contains("recvfrom failed: ECONNRESET (Connection reset by peer)")) {
//                Toast.makeText(Activityquiz.this, "Please try after some times...", Toast.LENGTH_LONG).show();
//            } else if (jsonArray.length() > 0) {
//
//                Intent intent = new Intent(Activityquiz.this,Dashboard.class);
//                startActivity(intent);
//                finish();
//
//
//                SharedPreferences pref2 = getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor1 = pref2.edit();
//                editor1.putString("Totalwallet", Totalwallet);
//                editor1.apply();
//
//            } else {
//                Toast.makeText(Activityquiz.this, "Log In Failed", Toast.LENGTH_LONG).show();
//            }
//        }
//        @Override
//        protected void onPreExecute() {
////            refcode = refe.getText().toString().trim();
//            hud = KProgressHUD.create(Activityquiz.this)
//                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                    .setWindowColor(Color.parseColor("#000000"))
//                    .setLabel("Please Wait...")
//                    .setAnimationSpeed(1);
//            hud.show();
//        }
//        @Override
//        protected void onProgressUpdate(Void... values) {
//        }
//    }

}
