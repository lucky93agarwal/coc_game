package com.challengers.of.call.Retrofit;

import com.challengers.of.call.Add_Money_Popup.FirstRequestJson;
import com.challengers.of.call.Add_Money_Popup.FirstResponseJson;
import com.challengers.of.call.Add_Money_Popup.SecondRequestJson;
import com.challengers.of.call.Add_Money_Popup.SecondResponseJson;
import com.challengers.of.call.BannedData.GetBannedResponseJson;
import com.challengers.of.call.Banned_APP.bannedRequestJson;
import com.challengers.of.call.ChallengersAPI.ChallengersGetRequestJson;
import com.challengers.of.call.ChallengersAPI.ChallengersGetResponseJson;
import com.challengers.of.call.ChallengersAPI.ChallengersRequestJson;
import com.challengers.of.call.Checksumnew;
import com.challengers.of.call.Cocquiz.AnswerRequestJson;
import com.challengers.of.call.Cocquiz.AnswerResponseJson;
import com.challengers.of.call.Cocquiz.GetCocResponseJson;
import com.challengers.of.call.Contest.ContestListResponseJson;
import com.challengers.of.call.Contest.ContestRequestJson;
import com.challengers.of.call.Contest.ContestResponseJson;
import com.challengers.of.call.Dashboardapidata.FirstAddWalletRequestJson;
import com.challengers.of.call.Dashboardapidata.FirstAddWalletResponseJson;
import com.challengers.of.call.Dashboardapidata.TokenRequestJson;
import com.challengers.of.call.FiFityAPI.FifityapiRequestJson;
import com.challengers.of.call.FiFityAPI.FifitybuyiRequestJson;
import com.challengers.of.call.FiFityAPI.FiftyfityResponseJson;
import com.challengers.of.call.FiFityAPI.FiftyfitybuyResponseJson;
import com.challengers.of.call.Frogetpass.FrogetPassRequestJson;
import com.challengers.of.call.Frogetpass.FrogetPassResponseJson;
import com.challengers.of.call.History.HistoryResponseJson;
import com.challengers.of.call.Leaderboarddata.LeaderboardRequestJson;
import com.challengers.of.call.Leaderboarddata.LeaderboardResponseJson;
import com.challengers.of.call.Logout.FeedbackRequestJson;
import com.challengers.of.call.Logout.LogoutRequestJson;
import com.challengers.of.call.Logout.LogoutResponseJson;
import com.challengers.of.call.MyContestAPI.MyAdapterRequestJson;
import com.challengers.of.call.MyContestAPI.MycontestRequestJson;
import com.challengers.of.call.MyContestAPI.MycontestResponseJson;
import com.challengers.of.call.Practice.GetPracticeResponseJson;
import com.challengers.of.call.Profiledata.ProfileImgRequestJson;
import com.challengers.of.call.Profiledata.ProfileImgResponseJson;
import com.challengers.of.call.Profiledata.ProfileRequestJson;
import com.challengers.of.call.Profiledata.ProfileResponseJson;
import com.challengers.of.call.Profiledata.ProfiledataResponseJson;
import com.challengers.of.call.Profiledata.WithdrawFiRequestJson;
import com.challengers.of.call.Profiledata.WithdrawFiResponseJson;
import com.challengers.of.call.Profiledata.WithdrawFinalRequestJson;
import com.challengers.of.call.Profiledata.WithdrawFinalResponseJson;
import com.challengers.of.call.Profiledata.WithdrawRequestJson;
import com.challengers.of.call.Profiledata.WithdrawResponseJson;
import com.challengers.of.call.QuestionWisResult.QuestionWisResultRequestJson;
import com.challengers.of.call.QuestionWisResult.QuestionWisResultResponseJson;
import com.challengers.of.call.ResetPass.ResetPassRequestJson;
import com.challengers.of.call.ResetPass.ResetpassResponseJson;
import com.challengers.of.call.ResultApi.ContinueRequestJson;
import com.challengers.of.call.ResultApi.ContinueResponseJson;
import com.challengers.of.call.ResultApi.QuitRequestJson;
import com.challengers.of.call.ResultApi.ShowResultRequestJson;
import com.challengers.of.call.ResultApi.ShowResultResponseJson;
import com.challengers.of.call.SignUpData.SignUpBtnCheckRequestJson;
import com.challengers.of.call.SignUpData.SignUpBtnCheckResponseJson;
import com.challengers.of.call.SignUpData.SignUpMobileCheckRequestJson;
import com.challengers.of.call.SignUpData.SignUpMobileCheckResponseJson;
import com.challengers.of.call.SignUpData.SignUpNameCheckRequestJson;
import com.challengers.of.call.SignUpData.SignUpNameCheckResponseJson;
import com.challengers.of.call.Signindata.FBLoginRequestJson;
import com.challengers.of.call.Signindata.FBLoginResponseJson;
import com.challengers.of.call.Signindata.LoginRequestJson;
import com.challengers.of.call.Signindata.LoginResponseJson;
import com.challengers.of.call.SocialCode.SocialOTPRequestJson;
import com.challengers.of.call.SocialCode.SocialOTPResponseJson;
import com.challengers.of.call.SplashData.VersionRequestJson;
import com.challengers.of.call.SplashData.VersionResponseJson;
import com.challengers.of.call.UpdateProfile.UpdatePasswordRequestJson;
import com.challengers.of.call.UpdateProfile.UpdateProfileRequestJson;
import com.challengers.of.call.UpdateProfile.UpdateProfileResponseJson;
import com.challengers.of.call.WinnerdetailAPI.WinnerdetailsRequestJson;
import com.challengers.of.call.WinnerdetailAPI.WinnerdetailsResponseJson;
import com.challengers.of.call.pytmclass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {



    @POST("services/check_version")
    Call<VersionResponseJson> checkVersion(@Body VersionRequestJson param);

    @POST("services/login")
    Call<LoginResponseJson> signin(@Body LoginRequestJson param);

    @POST("services/fblogin")
    Call<FBLoginResponseJson> fblogin(@Body FBLoginRequestJson param);


    @POST("services/logout")
    Call<LogoutResponseJson> getlogout(@Body LogoutRequestJson param);

    @POST("services/user_feedback")
    Call<LogoutResponseJson> getfeedback(@Body FeedbackRequestJson param);


    @POST("services/check_mobile")
    Call<SignUpMobileCheckResponseJson> signupmobile(@Body SignUpMobileCheckRequestJson param);

    @POST("services/check_username")
    Call<SignUpNameCheckResponseJson> signupname(@Body SignUpNameCheckRequestJson param);

    @POST("services/signup")
    Call<SignUpBtnCheckResponseJson> signupbtn(@Body SignUpBtnCheckRequestJson param);

    @POST("services/forget_password")
    Call<FrogetPassResponseJson> forgetpass(@Body FrogetPassRequestJson param);

    @POST("services/change_password")
    Call<ResetpassResponseJson> resetpass(@Body ResetPassRequestJson param);

    @POST("services/register_token")
    Call<SignUpNameCheckResponseJson> token(@Body TokenRequestJson param);

    @POST("services/signup")
    Call<SocialOTPResponseJson> socialcodeotp(@Body SocialOTPRequestJson param);

    @POST("services/upload_profile")
    Call<ProfileImgResponseJson> profileimg(@Body ProfileImgRequestJson param);

    @POST("services/user_profile")
    Call<ProfiledataResponseJson> profiledashboard(@Body ProfileRequestJson param);

    @POST("services/valid_amount")
    Call<WithdrawResponseJson> profilewithbtn(@Body WithdrawRequestJson param);

    @POST("services/withdraw_req")
    Call<WithdrawFinalResponseJson> profilefinal(@Body WithdrawFinalRequestJson param);

    @POST("services/update_profile")
    Call<UpdateProfileResponseJson> getupdate(@Body UpdateProfileRequestJson param);


    @POST("services/change_secret")
    Call<ContinueResponseJson> updatepass(@Body UpdatePasswordRequestJson param);

    @POST("services/valid_amount")
    Call<WithdrawResponseJson> profilewithamount(@Body WithdrawRequestJson param);

    @POST("services/wallet_data")
    Call<FirstAddWalletResponseJson> addwallet(@Body FirstAddWalletRequestJson param);

    @POST("services/my_wallet")
    Call<ProfileResponseJson> profiledata(@Body ProfileRequestJson param);

    @POST("services/wallet_history")
    Call<HistoryResponseJson> history(@Body ProfileRequestJson param);

    @POST("services/leaderboard")
    Call<LeaderboardResponseJson> leaderboard(@Body LeaderboardRequestJson param);

    @POST("services/fifty_price_list")
    Call<FiftyfityResponseJson> fifity(@Body FifityapiRequestJson param);

    @POST("services/buy_fifty")
    Call<FiftyfitybuyResponseJson> fifitybuy(@Body FifitybuyiRequestJson param);

    @POST("services/contest_create")
    Call<ContestResponseJson> contest(@Body ContestRequestJson param);

    @POST("services/challenger_list")
    Call<ContestListResponseJson> challenger(@Body ChallengersRequestJson param);

    @POST("services/contest_join")
    Call<ChallengersGetResponseJson> challengerget(@Body ChallengersGetRequestJson param);


    @POST("services/my_contest")
    Call<MycontestResponseJson> mycontest(@Body MycontestRequestJson param);

    @POST("services/contest_refund")
    Call<ContinueResponseJson> mycontestrefund(@Body MyAdapterRequestJson param);

    @POST("services/user_profile")
    Call<WinnerdetailsResponseJson> winnerdetails(@Body WinnerdetailsRequestJson param);

    @POST("services/verify_checksum")
    Call<FirstResponseJson> firstadd(@Body FirstRequestJson param);

    @POST("services/generate_checksum")
    Call<Checksumnew> checksum(@Body pytmclass param);

    @POST("services/add_money")
    Call<SecondResponseJson> secondadd(@Body SecondRequestJson param);

    @POST("services/withdrawfi")
    Call<WithdrawFiResponseJson> withdrawfi(@Body WithdrawFiRequestJson param);


    @POST("services/result_list")
    Call<ShowResultResponseJson> resultshow(@Body ShowResultRequestJson param);

    @POST("services/contest_continue")
    Call<ContinueResponseJson> resultcontinue(@Body ContinueRequestJson param);

    @POST("services/contest_quit")
    Call<ContinueResponseJson> resultquit(@Body QuitRequestJson param);

    @POST("services/que_response")
    Call<AnswerResponseJson> answerquit(@Body AnswerRequestJson param);

    @POST("services/result_sheet")
    Call<QuestionWisResultResponseJson> resultsheet(@Body QuestionWisResultRequestJson param);

    @POST("services/banned_games")
    Call<GetBannedResponseJson> getBanned(@Body bannedRequestJson param);

    @GET("services/practice_arena_list")
    Call<GetPracticeResponseJson> getPractice();

    @GET("services/game_arena_list")
    Call<GetPracticeResponseJson> getGameList();


    @GET("services/question_list")
    Call<GetCocResponseJson> getquizone();


}
