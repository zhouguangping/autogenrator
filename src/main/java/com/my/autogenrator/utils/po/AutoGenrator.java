package com.my.autogenrator.utils.po;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

//演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class AutoGenrator {

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor("zgp");
		gc.setOpen(false);
		gc.setSwagger2(true);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
//     dsc.setUrl("jdbc:mysql://localhost:3306/mkqy?useUnicode=true&useSSL=false&characterEncoding=utf8");
		dsc.setUrl(
				"jdbc:mysql://47.92.240.63:3306/data-governance?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&autoReconnect=true");
		// dsc.setSchemaName("public");
//     dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("htcx654321#");
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.aerotrust");
		pc.setModuleName("data.governance");
		pc.setEntity("pojo.po");
		pc.setMapper("dao.mapper");
		pc.setService("service");
		pc.setServiceImpl("service.impl");
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		String templatePath;
		// 如果模板引擎是 freemarker
		templatePath = "/templates/mapper.xml.ftl";
		// 如果模板引擎是 velocity
//		templatePath = "/templates/mapper.xml.vm";
//		templatePath = "/templates/mapper.xml.btl";

		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/"
						+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
		/*
		 * cfg.setFileCreate(new IFileCreate() {
		 * 
		 * @Override public boolean isCreate(ConfigBuilder configBuilder, FileType
		 * fileType, String filePath) { // 判断自定义文件夹是否需要创建 checkDir("调用默认方法创建的目录");
		 * return false; } });
		 */
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();

		// 配置自定义输出模板
		// 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		 templateConfig.setEntity("/com/my/autogenrator/utils/po/entity.java");
//		 templateConfig.setService();
//		 templateConfig.setController();

		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();

		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		//字段注解
		strategy.setEntityTableFieldAnnotationEnable(true);
		strategy.setEntityLombokModel(false);
		strategy.setRestControllerStyle(true);
		// 公共父类
//     strategy.setSuperControllerClass("com.my.autogenrator.utils.BaseController");
		// 写于父类中的公共字段
//     strategy.setSuperEntityClass("com.my.autogenrator.utils.BaseEntity");
//     strategy.setSuperEntityColumns("ID");
//     strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
//		strategy.setInclude(new String[]{"opc_server","opc_app"});
//		strategy.setLikeTable(new LikeTable("modbus_"));
		strategy.setControllerMappingHyphenStyle(true);
		strategy.setTablePrefix("tb_");//去除前缀
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

}
