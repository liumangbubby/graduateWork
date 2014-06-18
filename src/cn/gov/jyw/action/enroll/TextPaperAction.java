package cn.gov.jyw.action.enroll;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.gov.jyw.pojo.stu_enroll;
import cn.gov.jyw.service.ExamService;
import cn.gov.jyw.service.StuEnrollService;
import cn.gov.jyw.service.TbUserService;
import cn.gov.jyw.util.ExportPaper;

import com.opensymphony.xwork2.ActionSupport;

public class TextPaperAction extends ActionSupport{
	private int userid;
	private StuEnrollService stuServ;
	private TbUserService userServ;
	private ExamService examServ;
	private List<Map> examlist;
	private ExportPaper ep;
	private String username;
	private int score;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String answer5;
	private String answer6;
	private String answer7;
	private String answer8;
	private String answer9;
	private String answer10;
	private String answer11;
	private String answer12;
	private String answer13;
	private String answer14;
	private String answer15;
	private String answer16;
	private String answer17;
	private String answer18;
	private String answer19;
	private String answer20;
	private String answer21;
	private String answer22;
	private String answer23;
	private String answer24;
	private String answer25;
	private String answer26;
	private String answer27;
	private String answer28;
	private String answer29;
	private String answer30;
	private String answer31;
	private String answer32;
	private String answer33;
	private String answer34;
	private String answer35;
	private String answer36;
	private String answer37;
	private String answer38;
	private String answer39;
	private String answer40;
	private String answer41;
	private String answer42;
	private String answer43;
	private String answer44;
	private String answer45;
	private String answer46;
	private String answer47;
	private String answer48;
	private String answer49;
	private String answer50;
	private String answer51;
	private String answer52;
	private String answer53;
	private String answer54;
	private String answer55;
	private String answer56;
	private String answer57;
	private String answer58;
	private String answer59;
	private String answer60;
	private String answer61;
	private String answer62;
	private String answer63;
	private String answer64;
	private String answer65;
	private String answer66;
	private String answer67;
	private String answer68;
	private String answer69;
	private String answer70;
	private String answer71;
	private String answer72;
	private String answer73;
	private String answer74;
	private String answer75;
	private String answer76;
	private String answer77;
	private String answer78;
	private String answer79;
	private String answer80;
	private String answer81;
	private String answer82;
	private String answer83;
	private String answer84;
	private String answer85;
	private String answer86;
	private String answer87;
	private String answer88;
	private String answer89;
	private String answer90;
	private String answer91;
	private String answer92;
	private String answer93;
	private String answer94;
	private String answer95;
	private String answer96;
	private String answer97;
	private String answer98;
	private String answer99;
	private String answer100;
	
	
	//前分发
	public String toPaper(){
		long enrollid = userServ.getUser(userid).getEnrollId();
		stu_enroll enroll = stuServ.selectById(enrollid);
		String type = enroll.getSpecialty_submit();
		username = enroll.getName();
		int typeid = 0;
		if(type.contains("C1")){
			typeid = ExportPaper.C1;
		}else if(type.contains("C2")){
			typeid = ExportPaper.C2;
		}else if(type.contains("C3")){
			typeid = ExportPaper.C3;
		}else if(type.contains("C4")){
			typeid = ExportPaper.C4;
		}else if(type.contains("C5")){
			typeid = ExportPaper.C5;
		}else if(type.contains("A1")){
			typeid = ExportPaper.A1;
		}else if(type.contains("A2")){
			typeid = ExportPaper.A2;
		}else if(type.contains("A3")){
			typeid = ExportPaper.A3;
		}else if(type.contains("B1")){
			typeid = ExportPaper.B1;
		}else if(type.contains("B2")){
			typeid = ExportPaper.B2;
		}
		if(typeid != 0){
			examlist = ep.exportByType(typeid);
		}
		return this.SUCCESS;
	}
	//提交处理
	public String submitPaper(){
		Class c = this.getClass();
		Field[] fields = c.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		for(int i = 0 ; i < fields.length ; i++){
			System.out.println(fields[i].getName().contains("answer"));
			System.out.println(fields[i].getGenericType().toString().contains("class java.lang.String"));
			if(fields[i].getName().contains("answer") && fields[i].getGenericType().toString().equals("class java.lang.String")){
				String answer = "";
				try {
					Method m = c.getMethod("get" + this.getMethodName(fields[i].getName()));
					answer = (String) m.invoke(this);
				} catch (NoSuchMethodException e) {
					System.out.println("没函数");
					e.printStackTrace();
				} catch (SecurityException e) {
					System.out.println("拒绝访问");
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				list.add(answer);
			}
		}
		score = this.checkAnswer(list);
		userServ.updateExamScore(userid, score);
		return "finish";
	}
	
	/////////////////////////////////////////////////////
	public int checkAnswer(List<String> list){
		int score = 0;
		for(String ans : list){
			if(ans != null){
				String[] an = ans.split("@");
				if(examServ.checkAnswer(Integer.parseInt(an[0]), Integer.parseInt(an[1]))){
					score++;
				}
			}
		}
		return score;
	}
	private String getMethodName(String fildeName) throws Exception{  
        byte[] items = fildeName.getBytes();  
        items[0] = (byte) ((char) items[0] - 'a' + 'A');  
        return new String(items);  
    }  
	
	//////////////////////////////////////////////////////
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public StuEnrollService getStuServ() {
		return stuServ;
	}
	public void setStuServ(StuEnrollService stuServ) {
		this.stuServ = stuServ;
	}
	public TbUserService getUserServ() {
		return userServ;
	}
	public void setUserServ(TbUserService userServ) {
		this.userServ = userServ;
	}
	public List<Map> getExamlist() {
		return examlist;
	}
	public void setExamlist(List<Map> examlist) {
		this.examlist = examlist;
	}
	public ExportPaper getEp() {
		return ep;
	}
	public void setEp(ExportPaper ep) {
		this.ep = ep;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getAnswer4() {
		return answer4;
	}
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	public String getAnswer5() {
		return answer5;
	}
	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}
	public String getAnswer6() {
		return answer6;
	}
	public void setAnswer6(String answer6) {
		this.answer6 = answer6;
	}
	public String getAnswer7() {
		return answer7;
	}
	public void setAnswer7(String answer7) {
		this.answer7 = answer7;
	}
	public String getAnswer8() {
		return answer8;
	}
	public void setAnswer8(String answer8) {
		this.answer8 = answer8;
	}
	public String getAnswer9() {
		return answer9;
	}
	public void setAnswer9(String answer9) {
		this.answer9 = answer9;
	}
	public String getAnswer10() {
		return answer10;
	}
	public void setAnswer10(String answer10) {
		this.answer10 = answer10;
	}
	public String getAnswer11() {
		return answer11;
	}
	public void setAnswer11(String answer11) {
		this.answer11 = answer11;
	}
	public String getAnswer12() {
		return answer12;
	}
	public void setAnswer12(String answer12) {
		this.answer12 = answer12;
	}
	public String getAnswer13() {
		return answer13;
	}
	public void setAnswer13(String answer13) {
		this.answer13 = answer13;
	}
	public String getAnswer14() {
		return answer14;
	}
	public void setAnswer14(String answer14) {
		this.answer14 = answer14;
	}
	public String getAnswer15() {
		return answer15;
	}
	public void setAnswer15(String answer15) {
		this.answer15 = answer15;
	}
	public String getAnswer16() {
		return answer16;
	}
	public void setAnswer16(String answer16) {
		this.answer16 = answer16;
	}
	public String getAnswer17() {
		return answer17;
	}
	public void setAnswer17(String answer17) {
		this.answer17 = answer17;
	}
	public String getAnswer18() {
		return answer18;
	}
	public void setAnswer18(String answer18) {
		this.answer18 = answer18;
	}
	public String getAnswer19() {
		return answer19;
	}
	public void setAnswer19(String answer19) {
		this.answer19 = answer19;
	}
	public String getAnswer20() {
		return answer20;
	}
	public void setAnswer20(String answer20) {
		this.answer20 = answer20;
	}
	public String getAnswer21() {
		return answer21;
	}
	public void setAnswer21(String answer21) {
		this.answer21 = answer21;
	}
	public String getAnswer22() {
		return answer22;
	}
	public void setAnswer22(String answer22) {
		this.answer22 = answer22;
	}
	public String getAnswer23() {
		return answer23;
	}
	public void setAnswer23(String answer23) {
		this.answer23 = answer23;
	}
	public String getAnswer24() {
		return answer24;
	}
	public void setAnswer24(String answer24) {
		this.answer24 = answer24;
	}
	public String getAnswer25() {
		return answer25;
	}
	public void setAnswer25(String answer25) {
		this.answer25 = answer25;
	}
	public String getAnswer26() {
		return answer26;
	}
	public void setAnswer26(String answer26) {
		this.answer26 = answer26;
	}
	public String getAnswer27() {
		return answer27;
	}
	public void setAnswer27(String answer27) {
		this.answer27 = answer27;
	}
	public String getAnswer28() {
		return answer28;
	}
	public void setAnswer28(String answer28) {
		this.answer28 = answer28;
	}
	public String getAnswer29() {
		return answer29;
	}
	public void setAnswer29(String answer29) {
		this.answer29 = answer29;
	}
	public String getAnswer30() {
		return answer30;
	}
	public void setAnswer30(String answer30) {
		this.answer30 = answer30;
	}
	public String getAnswer31() {
		return answer31;
	}
	public void setAnswer31(String answer31) {
		this.answer31 = answer31;
	}
	public String getAnswer32() {
		return answer32;
	}
	public void setAnswer32(String answer32) {
		this.answer32 = answer32;
	}
	public String getAnswer33() {
		return answer33;
	}
	public void setAnswer33(String answer33) {
		this.answer33 = answer33;
	}
	public String getAnswer34() {
		return answer34;
	}
	public void setAnswer34(String answer34) {
		this.answer34 = answer34;
	}
	public String getAnswer35() {
		return answer35;
	}
	public void setAnswer35(String answer35) {
		this.answer35 = answer35;
	}
	public String getAnswer36() {
		return answer36;
	}
	public void setAnswer36(String answer36) {
		this.answer36 = answer36;
	}
	public String getAnswer37() {
		return answer37;
	}
	public void setAnswer37(String answer37) {
		this.answer37 = answer37;
	}
	public String getAnswer38() {
		return answer38;
	}
	public void setAnswer38(String answer38) {
		this.answer38 = answer38;
	}
	public String getAnswer39() {
		return answer39;
	}
	public void setAnswer39(String answer39) {
		this.answer39 = answer39;
	}
	public String getAnswer40() {
		return answer40;
	}
	public void setAnswer40(String answer40) {
		this.answer40 = answer40;
	}
	public String getAnswer41() {
		return answer41;
	}
	public void setAnswer41(String answer41) {
		this.answer41 = answer41;
	}
	public String getAnswer42() {
		return answer42;
	}
	public void setAnswer42(String answer42) {
		this.answer42 = answer42;
	}
	public String getAnswer43() {
		return answer43;
	}
	public void setAnswer43(String answer43) {
		this.answer43 = answer43;
	}
	public String getAnswer44() {
		return answer44;
	}
	public void setAnswer44(String answer44) {
		this.answer44 = answer44;
	}
	public String getAnswer45() {
		return answer45;
	}
	public void setAnswer45(String answer45) {
		this.answer45 = answer45;
	}
	public String getAnswer46() {
		return answer46;
	}
	public void setAnswer46(String answer46) {
		this.answer46 = answer46;
	}
	public String getAnswer47() {
		return answer47;
	}
	public void setAnswer47(String answer47) {
		this.answer47 = answer47;
	}
	public String getAnswer48() {
		return answer48;
	}
	public void setAnswer48(String answer48) {
		this.answer48 = answer48;
	}
	public String getAnswer49() {
		return answer49;
	}
	public void setAnswer49(String answer49) {
		this.answer49 = answer49;
	}
	public String getAnswer50() {
		return answer50;
	}
	public void setAnswer50(String answer50) {
		this.answer50 = answer50;
	}
	public String getAnswer51() {
		return answer51;
	}
	public void setAnswer51(String answer51) {
		this.answer51 = answer51;
	}
	public String getAnswer52() {
		return answer52;
	}
	public void setAnswer52(String answer52) {
		this.answer52 = answer52;
	}
	public String getAnswer53() {
		return answer53;
	}
	public void setAnswer53(String answer53) {
		this.answer53 = answer53;
	}
	public String getAnswer54() {
		return answer54;
	}
	public void setAnswer54(String answer54) {
		this.answer54 = answer54;
	}
	public String getAnswer55() {
		return answer55;
	}
	public void setAnswer55(String answer55) {
		this.answer55 = answer55;
	}
	public String getAnswer56() {
		return answer56;
	}
	public void setAnswer56(String answer56) {
		this.answer56 = answer56;
	}
	public String getAnswer57() {
		return answer57;
	}
	public void setAnswer57(String answer57) {
		this.answer57 = answer57;
	}
	public String getAnswer58() {
		return answer58;
	}
	public void setAnswer58(String answer58) {
		this.answer58 = answer58;
	}
	public String getAnswer59() {
		return answer59;
	}
	public void setAnswer59(String answer59) {
		this.answer59 = answer59;
	}
	public String getAnswer60() {
		return answer60;
	}
	public void setAnswer60(String answer60) {
		this.answer60 = answer60;
	}
	public String getAnswer61() {
		return answer61;
	}
	public void setAnswer61(String answer61) {
		this.answer61 = answer61;
	}
	public String getAnswer62() {
		return answer62;
	}
	public void setAnswer62(String answer62) {
		this.answer62 = answer62;
	}
	public String getAnswer63() {
		return answer63;
	}
	public void setAnswer63(String answer63) {
		this.answer63 = answer63;
	}
	public String getAnswer64() {
		return answer64;
	}
	public void setAnswer64(String answer64) {
		this.answer64 = answer64;
	}
	public String getAnswer65() {
		return answer65;
	}
	public void setAnswer65(String answer65) {
		this.answer65 = answer65;
	}
	public String getAnswer66() {
		return answer66;
	}
	public void setAnswer66(String answer66) {
		this.answer66 = answer66;
	}
	public String getAnswer67() {
		return answer67;
	}
	public void setAnswer67(String answer67) {
		this.answer67 = answer67;
	}
	public String getAnswer68() {
		return answer68;
	}
	public void setAnswer68(String answer68) {
		this.answer68 = answer68;
	}
	public String getAnswer69() {
		return answer69;
	}
	public void setAnswer69(String answer69) {
		this.answer69 = answer69;
	}
	public String getAnswer70() {
		return answer70;
	}
	public void setAnswer70(String answer70) {
		this.answer70 = answer70;
	}
	public String getAnswer71() {
		return answer71;
	}
	public void setAnswer71(String answer71) {
		this.answer71 = answer71;
	}
	public String getAnswer72() {
		return answer72;
	}
	public void setAnswer72(String answer72) {
		this.answer72 = answer72;
	}
	public String getAnswer73() {
		return answer73;
	}
	public void setAnswer73(String answer73) {
		this.answer73 = answer73;
	}
	public String getAnswer74() {
		return answer74;
	}
	public void setAnswer74(String answer74) {
		this.answer74 = answer74;
	}
	public String getAnswer75() {
		return answer75;
	}
	public void setAnswer75(String answer75) {
		this.answer75 = answer75;
	}
	public String getAnswer76() {
		return answer76;
	}
	public void setAnswer76(String answer76) {
		this.answer76 = answer76;
	}
	public String getAnswer77() {
		return answer77;
	}
	public void setAnswer77(String answer77) {
		this.answer77 = answer77;
	}
	public String getAnswer78() {
		return answer78;
	}
	public void setAnswer78(String answer78) {
		this.answer78 = answer78;
	}
	public String getAnswer79() {
		return answer79;
	}
	public void setAnswer79(String answer79) {
		this.answer79 = answer79;
	}
	public String getAnswer80() {
		return answer80;
	}
	public void setAnswer80(String answer80) {
		this.answer80 = answer80;
	}
	public String getAnswer81() {
		return answer81;
	}
	public void setAnswer81(String answer81) {
		this.answer81 = answer81;
	}
	public String getAnswer82() {
		return answer82;
	}
	public void setAnswer82(String answer82) {
		this.answer82 = answer82;
	}
	public String getAnswer83() {
		return answer83;
	}
	public void setAnswer83(String answer83) {
		this.answer83 = answer83;
	}
	public String getAnswer84() {
		return answer84;
	}
	public void setAnswer84(String answer84) {
		this.answer84 = answer84;
	}
	public String getAnswer85() {
		return answer85;
	}
	public void setAnswer85(String answer85) {
		this.answer85 = answer85;
	}
	public String getAnswer86() {
		return answer86;
	}
	public void setAnswer86(String answer86) {
		this.answer86 = answer86;
	}
	public String getAnswer87() {
		return answer87;
	}
	public void setAnswer87(String answer87) {
		this.answer87 = answer87;
	}
	public String getAnswer88() {
		return answer88;
	}
	public void setAnswer88(String answer88) {
		this.answer88 = answer88;
	}
	public String getAnswer89() {
		return answer89;
	}
	public void setAnswer89(String answer89) {
		this.answer89 = answer89;
	}
	public String getAnswer90() {
		return answer90;
	}
	public void setAnswer90(String answer90) {
		this.answer90 = answer90;
	}
	public String getAnswer91() {
		return answer91;
	}
	public void setAnswer91(String answer91) {
		this.answer91 = answer91;
	}
	public String getAnswer92() {
		return answer92;
	}
	public void setAnswer92(String answer92) {
		this.answer92 = answer92;
	}
	public String getAnswer93() {
		return answer93;
	}
	public void setAnswer93(String answer93) {
		this.answer93 = answer93;
	}
	public String getAnswer94() {
		return answer94;
	}
	public void setAnswer94(String answer94) {
		this.answer94 = answer94;
	}
	public String getAnswer95() {
		return answer95;
	}
	public void setAnswer95(String answer95) {
		this.answer95 = answer95;
	}
	public String getAnswer96() {
		return answer96;
	}
	public void setAnswer96(String answer96) {
		this.answer96 = answer96;
	}
	public String getAnswer97() {
		return answer97;
	}
	public void setAnswer97(String answer97) {
		this.answer97 = answer97;
	}
	public String getAnswer98() {
		return answer98;
	}
	public void setAnswer98(String answer98) {
		this.answer98 = answer98;
	}
	public String getAnswer99() {
		return answer99;
	}
	public void setAnswer99(String answer99) {
		this.answer99 = answer99;
	}
	public String getAnswer100() {
		return answer100;
	}
	public void setAnswer100(String answer100) {
		this.answer100 = answer100;
	}
	public ExamService getExamServ() {
		return examServ;
	}
	public void setExamServ(ExamService examServ) {
		this.examServ = examServ;
	}
}
