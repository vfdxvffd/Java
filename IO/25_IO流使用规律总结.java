明确一：要操作的数据是数据源还是数据目的
	源  ：InputStream		Reader
	目的：OutputStream		Writer
先根据需求明确要读，还是要写


明确二：要操作的设备上的数据是字节还是文本呢？
	源：
		字节：InputStream
		文本：Reader
	目的：
		字节：OutputStream
		文本：Writer
已经明确到了具体的体系上


明确三：明确数据所在的具体设备
	源设备：
		硬盘：文件  File开头的都是
		内存：数组，字符串
		键盘：System.in
		网络：Socket

	目的设备：
		硬盘：文件  File开头的都是
		内存：数组，字符串
		屏幕：System.out
		网络：Socket
完全可以明确具体要使用哪个流对象


明确四：是否需要额外功能呢？
	额外功能：
		需要转换吗： 转换流（需要桥梁吗？编码转换吗？）
		需要高效吗： 缓冲区对象
