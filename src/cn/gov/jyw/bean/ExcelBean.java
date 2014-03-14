package cn.gov.jyw.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.cxf.jaxrs.provider.json.utils.JSONUtils;
import org.apache.poi.hpsf.Util;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.google.gson.Gson;

import cn.gov.jyw.bo.StuRollCell;
import cn.gov.jyw.pojo.schedule;
import cn.gov.jyw.pojo.stop;
import cn.gov.jyw.pojo.stu_enroll;
import cn.gov.jyw.pojo.stu_record;

public class ExcelBean {
	private IDCard card;
	private CheckBean checkBean;
	private ZipUtil zipUtil;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private Gson gson = new Gson();

	/**
	 * 读取培训机构上传的Excel文件
	 * 
	 * @param path
	 *            Excel文件地址
	 * @return 读取的学员信息
	 * @throws Exception
	 */
	public HashMap readFile(String path) throws Exception {
		InputStream inp = null;
		HashMap map = new HashMap();
		try {
			ArrayList<StuRollCell> list = new ArrayList<StuRollCell>();
			inp = new FileInputStream(path);
			Workbook wb = new HSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(5);
			Cell cell = row.getCell(2);
			String s1 = cell.getStringCellValue();// 培训机构名称

			int last = sheet.getLastRowNum();

			HashSet<Integer> errorRows = new HashSet(); // 数据不完整列行号

			// 从Excel第八行开始读取学生信息
			for (int i = 7; i < last; i++) {
				row = sheet.getRow(i); // 当前行对象
				if (row == null) {
					continue; // 如果改行为空则跳过
				}
				cell = row.getCell(2);
				String s2 = cell.toString().trim(); // 姓名
				System.out.println(s2);

				cell = row.getCell(3);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s3 = cell.toString().trim(); // 性别

				cell = row.getCell(4);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s4 = cell.toString().trim(); // 民族

				cell = row.getCell(5);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s5 = cell.toString().trim(); // 身份证

				cell = row.getCell(6);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s6 = cell.toString().trim(); // 移动电话

				cell = row.getCell(7);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s7 = cell.toString().trim(); // 毕业学校

				cell = row.getCell(8);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s8 = cell.toString().trim(); // 毕业年届

				cell = row.getCell(9);
				Date s9 = null;
				try {
					s9 = cell.getDateCellValue(); // 毕业时间
				}
				catch (Exception e) {
					cell.setCellType(cell.CELL_TYPE_STRING);
					try {
						s9 = dateFormat.parse(cell.getStringCellValue().trim());
					}
					catch (Exception ex) {
						ex.printStackTrace();
						errorRows.add(i);// 日期格式不正确
						continue;
					}
				}

				cell = row.getCell(10);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s10 = cell.toString().trim();// 学历

				cell = row.getCell(11);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s11 = cell.toString().trim(); // 所学专业

				cell = row.getCell(12);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s12 = cell.toString().trim(); // 政治面貌

				cell = row.getCell(13);

				Date s13 = null;

				try {
					s13 = cell.getDateCellValue(); // 出生日期
				}
				catch (Exception e) {
					cell.setCellType(cell.CELL_TYPE_STRING);
					try {
						s13 = dateFormat.parse(cell.getStringCellValue().trim());
					}
					catch (Exception ex) {
						ex.printStackTrace();
						errorRows.add(i);// 日期格式不正确
						continue;
					}
				}

				cell = row.getCell(14);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s14 = cell.toString().trim(); // 现居住地

				cell = row.getCell(15);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s15 = cell.toString().trim(); // 户籍所在地

				cell = row.getCell(16);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s16 = cell.toString().trim(); // Email

				cell = row.getCell(17);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s17 = cell.toString().trim(); // 家庭住址

				cell = row.getCell(18);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s18 = cell.toString().trim(); // 家庭联系电话

				cell = row.getCell(19);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s19 = cell.toString().trim(); // 有何特长

				cell = row.getCell(20);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s20 = cell.toString().trim(); // 健康状况

				cell = row.getCell(21);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s21 = cell.toString().trim(); // 拟申报专业

				cell = row.getCell(22);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s22 = cell.toString().trim(); // 拟参加培训地点

				cell = row.getCell(23);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s23 = cell.toString().trim(); // 是否同意变更地点

				cell = row.getCell(24);
				cell.setCellType(cell.CELL_TYPE_STRING);
				String s24 = cell.toString().trim(); // 拟求职意向
				if(checkBean.checkName(s2)==false){
					System.out.println("姓名错误");
				}
				if(!checkBean.checkSex(s3)){
					System.out.println("性别错误");
				}
				if(!checkBean.checkNation(s4)){
					System.out.println("民族错误");
				}
				
				if(!checkBean.checkPid(s5)){
					System.out.println("身份证错误");
				}
				if(!checkBean.checkTel(s6)){
					System.out.println("电话错误");
				}
				if(!checkBean.checkGraduteG(s7)){
					System.out.println("毕业院校错误");
				}
				if(!checkBean.checkGraduteY(s8)){
					System.out.println("毕业年份错误");
				}
				if(!checkBean.checkEducation(s10)){
					System.out.println("学历错误");
				}
				if(!checkBean.checkSpecialty(s11)){
					System.out.println("专业错误");
				}
				if(!checkBean.checkPolitics(s12)){
					System.out.println("政治面貌错误");
				}
				if(!checkBean.checkAddress(s14)){
					System.out.println("居住地错误");
				}
				if(!checkBean.checHousehold(s15)){
					System.out.println("户籍错误");
				}
				
				if(!checkBean.checkEmail(s16)){
					System.out.println("邮件错误");
				}
				if(!checkBean.checkHomeAddress(s17)){
					System.out.println( "住址错误");
				}
				
				if(!checkBean.checkHomeTel(s18)){
					System.out.println("家庭电话错误");
				}
				if(!checkBean.checkHealthy(s20)){
					System.out.println("健康错误");
				}
				if(!checkBean.checkReport(s21)){
					System.out.println("申报专业错误");
				}
				if(!checkBean.checkPlace(s22)){
					System.out.println("申报地点错误");
				}
				
				if(!checkBean.checkChangePlace(s23)){
					System.out.println("改变地点错误");
				}
				if(!checkBean.checkIntention(s24)){
					System.out.println("求职意向错误" );
				}
				System.out.println("-------------------------------------------------------------------------------");
				
				// 开始验证各项填写内容
				if (checkBean.checkName(s2) && checkBean.checkSex(s3) && checkBean.checkNation(s4) && checkBean.checkPid(s5) && checkBean.checkTel(s6)
						&& checkBean.checkGraduteG(s7) && checkBean.checkGraduteY(s8) && checkBean.checkEducation(s10) && checkBean.checkSpecialty(s11)
						&& checkBean.checkPolitics(s12) && checkBean.checkAddress(s14) && checkBean.checHousehold(s15) && checkBean.checkEmail(s16)
						&& checkBean.checkHomeAddress(s17) && checkBean.checkHomeTel(s18) && checkBean.checkHealthy(s20) && checkBean.checkReport(s21)
						&& checkBean.checkPlace(s22) && checkBean.checkChangePlace(s23) && checkBean.checkIntention(s24)) {
					

					stu_enroll enroll = new stu_enroll();
					enroll.setName(s2);
					enroll.setSex(s3);
					enroll.setNation(s4);
					enroll.setPid(s5);
					enroll.setTel(s6);
					enroll.setGradute_g(s7);
					enroll.setGradute_y(s8);
					enroll.setGradute_d(this.format.format(s9));
					enroll.setEducation(s10);
					enroll.setSpecialty(s11);
					enroll.setPolitics(s12);
					String temp = IDCard.getBirthday(s5);

					enroll.setBirthday(temp);
					enroll.setAddress(s14);
					enroll.setHousehold(s15);
					enroll.setEmail(s16);
					enroll.setHome_address(s17);
					enroll.setHome_tel(s18);
					enroll.setSkilled(s19);
					enroll.setHealthy(s20);
					enroll.setEdu_school(s1);
					// TODO 这里缺少拟申报专业的设置(s21)
					enroll.setSpecialty_submit(s21);
					enroll.setPlace(s22);

					if (s23 == null || s23.length() == 0 || s23.equals("否")) {
						enroll.setChange_place(false);
					}
					else {
						enroll.setChange_place(true);
					}
					enroll.setIntention(s24);

					StuRollCell rollCell = new StuRollCell(i, enroll);

					list.add(rollCell); // 把数据格式正确的学生记录添加到List

				}
				else {
					errorRows.add(i); // 记录数据格式错误的行号
				}
			}
			if (errorRows.size() > 0) {
				// saveError(path, errorRows); // 数据不完整，需要前台弹出对话框
				map.put("code", 200);

			}
			else {
				map.put("code", 100);
			}
			map.put("list", list);
			map.put("errorRows", errorRows);
			return map;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			if (inp != null) {
				inp.close(); // 成功或者失败都要关闭IO连接
			}
		}
	}

	/**
	 * 标记Excel文件中行记录的背景色为红色
	 * 
	 * @param path
	 *            Excel文件地址
	 * @param rows
	 *            需要标记背景色的行号数组
	 * @throws Exception
	 */
	public void flagError(String path, ArrayList<Integer> rows) throws Exception {
		InputStream inp = null;
		FileOutputStream fileOut = null;
		try {
			inp = new FileInputStream(path);
			Workbook wb = new HSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);

			CellStyle style = wb.createCellStyle();
			style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
			style.setFillPattern(CellStyle.BIG_SPOTS);

			for (int i = 0; i < rows.size(); i++) {
				Row row = sheet.getRow(rows.get(i)); // Excel中学生信息在系统中重复的行记录
				row.setRowStyle(style); // 设置这一行的背景色为红色
			}
			fileOut = new FileOutputStream(path);
			wb.write(fileOut);

		}
		catch (Exception e) {
			throw e;
		}
		finally {
			if (inp != null) {
				inp.close();
			}
			if (fileOut != null) {
				fileOut.close();
			}
		}
	}

	/**
	 * 保留错误记录
	 * 
	 * @param path
	 *            Excel文件地址
	 * @param rows
	 * 
	 * @throws Exception
	 */
	public void saveError(String path, ArrayList<Integer> rows) throws Exception {
		InputStream inp = null;
		FileOutputStream fileOut = null;
		try {
			inp = new FileInputStream(path);
			HSSFWorkbook wb = new HSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);

			CellStyle style = wb.createCellStyle();
			style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
			style.setFillPattern(CellStyle.BIG_SPOTS);
			HashSet<Integer> set = new HashSet<Integer>();
			for (int i = 0; i < rows.size(); i++) {
				// Row row = sheet.getRow(rows.get(i)); // Excel中学生信息在系统中重复的行记录
				// row.setRowStyle(style); // 设置这一行的背景色为红色
				set.add(rows.get(i));
			}
			ArrayList list = new ArrayList();
			for (int i = 7; i <= sheet.getLastRowNum(); i++) {
				if (set.contains(i) == true) {
					list.add(sheet.getRow(i));
				}
			}
			// 删除所有数据
			for (int i = 7; i < sheet.getLastRowNum(); i++) {

				Row removingRow = sheet.getRow(i);
				if (removingRow != null) {
					sheet.removeRow(removingRow);
				}
			}

			int start = 7;
			// 设置边框
			style = wb.createCellStyle();
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setAlignment((short) 2);
			Font font = wb.createFont();
			font.setFontHeightInPoints((short) 10);// 设置字号
			style.setFont(font);
			HSSFDataFormat format = wb.createDataFormat();
			// 这样才能真正的控制单元格格式，@就是指文本型，具体格式的定义还是参考上面的原文吧
			style.setDataFormat(format.getFormat("@"));

			for (int i = 0; i < list.size(); i++) {
				Row one = (Row) list.get(i);
				Row row = sheet.createRow(start);

				Cell cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(i + 1); // 序号

				row.setHeight((short) 450);
				cell = row.createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue(one.getCell(2).toString().trim()); // 姓名

				cell = row.createCell(3);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(3).toString().trim()); // 性别

				cell = row.createCell(4);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(4).toString().trim()); // 民族

				cell = row.createCell(5);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(5).toString().trim()); // 身份证

				cell = row.createCell(6);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(6).toString().trim()); // 移动电话

				cell = row.createCell(7);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(7).toString().trim()); // 毕业学校

				cell = row.createCell(8);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(8).toString().trim()); // 毕业年届

				cell = row.createCell(9);
				cell.setCellStyle(style);
				Date s9 = cell.getDateCellValue(); // 毕业时间
				Date d9 = null;
				try {
					d9 = one.getCell(9).getDateCellValue();
				}
				catch (Exception e) {
					cell.setCellType(cell.CELL_TYPE_STRING);
					d9 = dateFormat.parse(one.getCell(9).getStringCellValue());
				}
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(dateFormat.format(d9));

				cell = row.createCell(10);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(10).toString().trim());// 学历

				cell = row.createCell(11);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(11).toString().trim()); // 所学专业

				cell = row.createCell(12);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(12).toString().trim()); // 政治面貌

				cell = row.createCell(13);
				cell.setCellStyle(style);
				Date s13 = cell.getDateCellValue(); // 出生日期
				Date d13 = null;
				try {
					d13 = one.getCell(13).getDateCellValue();
				}
				catch (Exception e) {
					cell.setCellType(cell.CELL_TYPE_STRING);
					d13 = dateFormat.parse(one.getCell(13).getStringCellValue());
				}
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(dateFormat.format(d13));

				cell = row.createCell(14);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(14).toString().trim()); // 现居住地

				cell = row.createCell(15);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(15).toString().trim()); // 户籍所在地

				cell = row.createCell(16);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(16).toString().trim());// Email

				cell = row.createCell(17);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(17).toString().trim()); // 家庭住址

				cell = row.createCell(18);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(18).toString().trim()); // 家庭联系电话

				cell = row.createCell(19);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(19).toString().trim()); // 有何特长

				cell = row.createCell(20);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(20).toString().trim()); // 健康状况

				cell = row.createCell(21);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(21).toString().trim()); // 拟申报专业

				cell = row.createCell(22);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(22).toString().trim()); // 拟参加培训地点

				cell = row.createCell(23);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(23).toString().trim());// 是否同意变更地点

				cell = row.createCell(24);
				cell.setCellStyle(style);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(one.getCell(24).toString().trim()); // 拟求职意向
				start++;
			}
			fileOut = new FileOutputStream(path);
			wb.write(fileOut);

		}
		catch (Exception e) {
			throw e;
		}
		finally {
			if (inp != null) {
				inp.close();
			}
			if (fileOut != null) {
				fileOut.close();
			}
		}
	}

	/**
	 * 导入Excel数据
	 * 
	 * @param floderPath
	 * @return
	 * @throws Exception
	 */
	public List<stu_record> importRecord(String floderPath) throws Exception {
		File floder = new File(floderPath); // 根文件夹

		File[] files = floder.listFiles();
		ArrayList<stu_record> list = new ArrayList<stu_record>();

		for (File file : files) {
			FileInputStream in = null;

			Workbook wb = null;
			try {

				in = new FileInputStream(file);
				wb = new HSSFWorkbook(in);

				Sheet sheet = wb.getSheetAt(0); // 第一个Sheet页

				int rowNum = 7;// 从第7行开始读取数据
				for (;;) {
					Row row = sheet.getRow(rowNum);
					Cell cell = row.getCell(2);// 1.序号
					cell.setCellType(cell.CELL_TYPE_STRING);
					String num = cell.getStringCellValue();
					if (num == null || num.length() == 0) {
						break; // 读取到结尾了
					}
					cell = row.getCell(3);
					cell.setCellType(cell.CELL_TYPE_STRING);
					String name = cell.getStringCellValue(); // 2.姓名

					// cell = row.getCell(4);
					// cell.setCellType(cell.CELL_TYPE_STRING);
					// String sex = cell.getStringCellValue(); // 3.性别

					// Date date = row.getCell(5).getDateCellValue();

					// String birthday = date != null ? dateFormat.format(date)
					// : null; // 4.生日

					// cell = row.getCell(6);
					// cell.setCellType(cell.CELL_TYPE_STRING);
					// String nation = cell.getStringCellValue(); // 5.民族

					// cell = row.getCell(7);
					// cell.setCellType(cell.CELL_TYPE_STRING);
					// String gradute_g = cell.getStringCellValue(); // 6.毕业学校

					// cell = row.getCell(8);
					// cell.setCellType(cell.CELL_TYPE_STRING);
					// String specialty = cell.getStringCellValue(); // 7.所学专业

					// cell = row.getCell(9);
					// cell.setCellType(cell.CELL_TYPE_STRING);
					// String household = cell.getStringCellValue(); // 8.户籍所在地

					cell = row.getCell(10);
					cell.setCellType(cell.CELL_TYPE_STRING);
					String pid = cell.getStringCellValue(); // 9.身份证

					// cell = row.getCell(11);
					// cell.setCellType(cell.CELL_TYPE_STRING);
					// String tel = cell.getStringCellValue();// 10.电话

					stu_record record = new stu_record();
					// TODO 姓名 身份证的验证
					record.setName(name);
					// record.setSex(sex);
					// record.setBirthday(birthday);
					// record.setNation(nation);
					// record.setGradute_g(gradute_g);
					// record.setSpecialty(specialty);
					// record.setHousehold(household);
					record.setPid(pid);
					// record.setTel(tel);
					list.add(record);

					rowNum++;

				}
			}
			catch (Exception e) {
				throw e;
			}
			finally {
				if (in != null) {
					in.close();
				}

			}

		}
		return list;
	}

	/**
	 * 导出学生报名表
	 * 
	 * @param website
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public String exportRecord(String website, List<stu_record> list) throws Exception {
		InputStream inp = null;
		FileOutputStream fileOut = null;
		String export = website + "export\\" + new Date().getTime() + "\\";
		try {

			String temp = ""; // 临时保存班级
			ArrayList list_1 = new ArrayList(); // 保存大的
			ArrayList list_2 = null;

			for (int i = 0; i < list.size(); i++) {
				stu_record record = list.get(i);
				if (i == 0) {
					list_2 = new ArrayList();
					temp = record.getClass_No();
				}
				if (temp.equals(record.getClass_No()) == false) {
					list_1.add(list_2);
					list_2 = new ArrayList();
				}
				list_2.add(record);
				temp = record.getClass_No();
				if (i == list.size() - 1) {
					list_1.add(list_2);
				}
			}

			for (int i = 0; i < list_1.size(); i++) {
				list_2 = (ArrayList) list_1.get(i);
				int start = 7;
				inp = new FileInputStream(website + "\\excel\\模板.xls");
				HSSFWorkbook wb = new HSSFWorkbook(inp);
				Sheet sheet = wb.getSheetAt(0);
				Row row = sheet.getRow(7);
				HSSFCellStyle style = wb.createCellStyle();
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style.setAlignment((short) 2);

				for (int j = 0; j < list_2.size(); j++) {
					stu_record record = (stu_record) list_2.get(j);
					// System.out.println(record.getClass_No() + "," +
					// record.getName() + "," + record.getSpecialty_Submit());
					row = sheet.createRow(start);

					Cell cell = row.createCell(2);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(j + 1); // 序号

					cell = row.createCell(3);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(record.getName()); // 姓名

					cell = row.createCell(4);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(record.getSex()); // 性别

					cell = row.createCell(5);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					Date date = format.parse(record.getBirthday());

					cell.setCellValue(dateFormat.format(date)); // 出生日期

					cell = row.createCell(6);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(record.getNation()); // 民族

					cell = row.createCell(7);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(record.getGradute_g()); // 毕业院校

					cell = row.createCell(8);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(record.getSpecialty()); // 所学专业

					cell = row.createCell(9);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(record.getHousehold()); // 户籍所在地

					cell = row.createCell(10);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(record.getPid()); // 身份证号码

					cell = row.createCell(11);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(record.getTel()); // 电话

					Row line = sheet.getRow(5);
					Cell cl = line.getCell(6);
					cl.setCellType(cl.CELL_TYPE_STRING);
					cl.setCellValue("培训专业：" + record.getSpecialty_Submit());

					cl = line.getCell(9);
					cl.setCellType(cl.CELL_TYPE_STRING);
					cl.setCellValue("总人数：" + list_2.size() + "人");

					File file = new File(export);
					file.mkdirs();

					fileOut = new FileOutputStream(export + record.getEdu_School() + "-" + record.getClass_No() + ".xls");
					wb.write(fileOut);
					start++;
				}
			}
			Date date = new Date();
			String out = website + "export\\" + date.getTime() + ".zip";
			zipUtil.zip(export, out);
			return "export/" + date.getTime() + ".zip";

		}
		catch (Exception e) {
			// e.printStackTrace();
			throw e;
		}
		finally {
			if (inp != null) {
				inp.close();
			}
			if (fileOut != null) {
				fileOut.close();
			}
		}

	}

	public String exportEnroll(String website, List<stu_enroll> list) throws Exception {
		InputStream inp = null;
		FileOutputStream fileOut = null;
		String export = website + "export\\" + new Date().getTime() + "\\";
		try {

			String temp = ""; // 临时保存班级
			ArrayList list_1 = new ArrayList(); // 保存大的
			ArrayList list_2 = null;

			for (int i = 0; i < list.size(); i++) {
				stu_enroll enroll = list.get(i);
				if (i == 0) {
					list_2 = new ArrayList();
					temp = enroll.getClass_no();
				}
				if (temp.equals(enroll.getClass_no()) == false) {
					list_1.add(list_2);
					list_2 = new ArrayList();
				}
				list_2.add(enroll);
				temp = enroll.getClass_no();
				if (i == list.size() - 1) {
					list_1.add(list_2);
				}
			}

			for (int i = 0; i < list_1.size(); i++) {
				list_2 = (ArrayList) list_1.get(i);
				int start = 7;
				inp = new FileInputStream(website + "\\excel\\模板.xls");
				HSSFWorkbook wb = new HSSFWorkbook(inp);
				Sheet sheet = wb.getSheetAt(0);
				Row row = sheet.getRow(7);
				HSSFCellStyle style = wb.createCellStyle();
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style.setAlignment((short) 2);

				for (int j = 0; j < list_2.size(); j++) {
					stu_enroll enroll = (stu_enroll) list_2.get(j);
					// System.out.println(enroll.getClass_no() + "," +
					// enroll.getName() + "," + enroll.getSpecialty_submit());
					row = sheet.createRow(start);

					Cell cell = row.createCell(2);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(j + 1); // 序号

					cell = row.createCell(3);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(enroll.getName()); // 姓名

					cell = row.createCell(4);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(enroll.getSex()); // 性别

					cell = row.createCell(5);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					Date date = format.parse(enroll.getBirthday());

					cell.setCellValue(dateFormat.format(date)); // 出生日期

					cell = row.createCell(6);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(enroll.getNation()); // 民族

					cell = row.createCell(7);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(enroll.getGradute_g()); // 毕业院校

					cell = row.createCell(8);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(enroll.getSpecialty()); // 所学专业

					cell = row.createCell(9);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(enroll.getHousehold()); // 户籍所在地

					cell = row.createCell(10);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(enroll.getPid()); // 身份证号码

					cell = row.createCell(11);
					cell.setCellStyle(style);
					cell.setCellType(cell.CELL_TYPE_STRING);
					cell.setCellValue(enroll.getTel()); // 电话

					Row line = sheet.getRow(5);
					Cell cl = line.getCell(6);
					cl.setCellType(cl.CELL_TYPE_STRING);
					cl.setCellValue("培训专业：" + enroll.getSpecialty_submit());

					cl = line.getCell(9);
					cl.setCellType(cl.CELL_TYPE_STRING);
					cl.setCellValue("总人数：" + list_2.size() + "人");

					File file = new File(export);
					file.mkdirs();

					fileOut = new FileOutputStream(export + enroll.getEdu_school() + "-" + enroll.getClass_no() + ".xls");
					wb.write(fileOut);
					start++;
				}
			}
			Date date = new Date();
			String out = website + "export\\" + date.getTime() + ".zip";
			zipUtil.zip(export, out);
			return "export/" + date.getTime() + ".zip";

		}
		catch (Exception e) {
			// e.printStackTrace();
			throw e;
		}
		finally {
			if (inp != null) {
				inp.close();
			}
			if (fileOut != null) {
				fileOut.close();
			}
		}

	}

	public String exportSchedule(String website, schedule s) throws Exception {
		InputStream inp = null;
		FileOutputStream fileOut = null;
		inp = new FileInputStream(website + "\\excel\\课表.xls");
		HSSFWorkbook wb = new HSSFWorkbook(inp);
		File file = new File(website + "export\\schedule\\");
		if (file.exists() == false) {
			file.mkdirs();
		}
		String export = website + "export\\schedule\\" + new Date().getTime() + ".xls";
		fileOut = new FileOutputStream(export);
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(0);
		row.getCell(0).setCellValue("辽宁省离校未就业高校毕业生专业转换及技能培训 第（" + s.getWeek() + "）周 课程表");

		// 日期
		row = sheet.getRow(3);

		ArrayList<String> day_1 = gson.fromJson(s.getDay_1(), ArrayList.class);
		ArrayList<String> day_2 = gson.fromJson(s.getDay_2(), ArrayList.class);
		ArrayList<String> day_3 = gson.fromJson(s.getDay_3(), ArrayList.class);
		ArrayList<String> day_4 = gson.fromJson(s.getDay_4(), ArrayList.class);
		ArrayList<String> day_5 = gson.fromJson(s.getDay_5(), ArrayList.class);
		ArrayList<String> subject = gson.fromJson(s.getSubject(), ArrayList.class);
		ArrayList<String> teacher = gson.fromJson(s.getTeacher(), ArrayList.class);
		ArrayList<String> number = gson.fromJson(s.getNumber(), ArrayList.class);
		ArrayList<String> place = gson.fromJson(s.getPlace(), ArrayList.class);
		ArrayList<String> remark = gson.fromJson(s.getRemark(), ArrayList.class);

		row.getCell(2).setCellValue(day_1.get(0));
		row.getCell(3).setCellValue(day_2.get(0));
		row.getCell(4).setCellValue(day_3.get(0));
		row.getCell(5).setCellValue(day_4.get(0));
		row.getCell(6).setCellValue(day_5.get(0));

		for (int i = 4; i <= 11; i++) {
			row = sheet.getRow(i);
			Cell c1 = row.getCell(0);
			c1.setCellValue(subject.get(i - 4));

			Cell c2 = row.getCell(2);
			c2.setCellValue(day_1.get(i - 4 + 1));

			Cell c3 = row.getCell(3);
			c3.setCellValue(day_2.get(i - 4 + 1));

			Cell c4 = row.getCell(4);
			c4.setCellValue(day_3.get(i - 4 + 1));

			Cell c5 = row.getCell(5);
			c5.setCellValue(day_4.get(i - 4 + 1));

			Cell c6 = row.getCell(6);
			c6.setCellValue(day_5.get(i - 4 + 1));
			
			Cell c7 = row.getCell(7);
			c7.setCellValue(teacher.get(i - 4));
			
			Cell c8 = row.getCell(8);
			c8.setCellValue(number.get(i - 4));
			
			Cell c9 = row.getCell(9);
			c9.setCellValue(place.get(i - 4));
			
			Cell c10 = row.getCell(10);
			c10.setCellValue(remark.get(i - 4));
		}

		wb.write(fileOut);
		inp.close();
		fileOut.close();
		return export;
	}
	
	public String exportStop(String website, List<stop> list,String school) throws Exception {
		InputStream inp = null;
		FileOutputStream fileOut = null;
		inp = new FileInputStream(website + "\\excel\\中途退出培训学生信息表.xls");
		HSSFWorkbook wb = new HSSFWorkbook(inp);
		File file = new File(website + "export\\stop\\");
		if (file.exists() == false) {
			file.mkdirs();
		}
		String export = website + "export\\stop\\" + new Date().getTime() + ".xls";
		//System.out.println(export);
		fileOut = new FileOutputStream(export);
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(1);
		Cell cell=row.getCell(0);
		cell.setCellValue("培训机构："+school);
		int j=3;
		for(int i=0;i<list.size();i++){
			Row r = sheet.createRow(j);
			Cell c1=r.createCell(0);
			c1.setCellValue(""+i);
			Cell c2=r.createCell(1);
			c2.setCellValue(list.get(i).getClass_no());
			Cell c3=r.createCell(2);
			Integer stuNo=list.get(i).getStu_no();
			
			c3.setCellValue(stuNo==null?"":stuNo+"");
			Cell c4=r.createCell(3);
			c4.setCellValue(list.get(i).getName());
			Cell c5=r.createCell(4);
			c5.setCellValue(list.get(i).getTel());
			Cell c6=r.createCell(5);
			c6.setCellValue(dateFormat.format(list.get(i).getJoin_date()));
			Cell c7=r.createCell(6);
			c7.setCellValue(dateFormat.format(list.get(i).getStop_date()));
			Cell c8=r.createCell(7);
			c8.setCellValue(""+list.get(i).getDays());
			Cell c9=r.createCell(8);
			c9.setCellValue(""+list.get(i).getReason());
			Cell c10=r.createCell(9);
			c10.setCellValue(""+list.get(i).getRemark());
			j++;
		}
		wb.write(fileOut);
		fileOut.flush();
		inp.close();
		fileOut.close();
		return export;
	}

	public IDCard getCard() {
		return card;
	}

	public void setCard(IDCard card) {
		this.card = card;
	}

	public CheckBean getCheckBean() {
		return checkBean;
	}

	public void setCheckBean(CheckBean checkBean) {
		this.checkBean = checkBean;
	}

	public static void main(String[] args) {
		ExcelBean bean = new ExcelBean();
		ArrayList list = new ArrayList();
		list.add("sds");
		list.add("sds");
		list.add("sds");
		list.add("sds");
		try {
			bean.exportRecord("D:\\1\\模板.xls", list);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ZipUtil getZipUtil() {
		return zipUtil;
	}

	public void setZipUtil(ZipUtil zipUtil) {
		this.zipUtil = zipUtil;
	}

}
