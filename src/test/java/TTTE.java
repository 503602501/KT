import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class TTTE {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		
		
		Date d1 = new SimpleDateFormat("yyyy-M-d").parse("2012-1-1");//定义起始日期

		Date d2 = new SimpleDateFormat("yyyy-M-d").parse("2015-1-1");//定义结束日期
		Calendar dd = Calendar.getInstance();//定义日期实例

		dd.setTime(d1);//设置日期起始时间

		while(dd.getTime().before(d2)){//判断是否到结束日期

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");

		String str = sdf.format(dd.getTime());

		System.out.println("http://c.spdex.com/MatchHistory?date="+str);//输出日期结果

		dd.add(Calendar.DATE, 1);//进行当前日期月份加1

		}
		
		
		
//		String s = "http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601044_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601023_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601029_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601048_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601049_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601044_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601001_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601002_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601003_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601004_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601005_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601006_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601007_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601008_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601009_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601010_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601011_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601012_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601013_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601014_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601015_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601016_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601017_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601018_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601019_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601020_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601021_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601022_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601023_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601024_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601025_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601026_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601027_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601028_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601029_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601030_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601031_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601032_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601033_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601034_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601035_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601036_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601037_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601038_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601039_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601040_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601041_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601042_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601043_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601044_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601045_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601046_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601047_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601048_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601049_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601050_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601051_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601052_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601053_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601054_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601055_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601056_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601057_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601058_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601059_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601060_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601061_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601062_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601063_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601064_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601065_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601066_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601067_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601068_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601069_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601070_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601071_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601072_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601073_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601074_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601075_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601076_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601077_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601078_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601079_750.jpg,http://2mm.i.ximgs.net/9/218929/20180121/20180121210234601080_750.jpg";
//		System.out.println(s.split("http").length);
//		Runtime rt = Runtime.getRuntime();
//		Process p = rt.exec("phantomjs.exe");
	/*	JFrame frame = new JFrame();
		frame.setSize(800, 500);
		InfiniteProgressPanel glasspane = new InfiniteProgressPanel();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); glasspane.setBounds(100, 100, (dimension.width) / 2, (dimension.height) / 2);
		frame.setGlassPane(glasspane);
		glasspane.start();//开始动画加载效果
		frame.setVisible(true);*/
		 
		// Later, to disable,在合适的地方关闭动画效果
//		glasspane.stop();
		/*ImageProperty imageProperty = new ImageProperty();
		imageProperty.setMinHeight(100);
		imageProperty.setMinSize(20);
		String s = JSONObject.toJSONString(imageProperty);
		System.out.println(s);
		FolderUtil.createFile(new File("config/serialize.json"), s);
		String content = FolderUtil.readFileContent(new File("config/serialize.json"));
		System.out.println(content);
		JSONObject jsonObject=JSONObject.parseObject(content);
		ImageProperty image =  JSONObject.toJavaObject(jsonObject, ImageProperty.class);
//		ImageProperty image =  JSONObject.toB (JSONObject.toJSONString(content), ImageProperty.class);
		 System.out.println(image);
//		ImageProperty s = JSONObject.toJavaObject(JSONObject.class.cast(imageProperty), ImageProperty.class);
//		serializeProxy
*/
	}

}
