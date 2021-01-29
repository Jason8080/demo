package cn.huolala.gm.demo;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.util.StringUtils;

import java.util.*;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {
    // -----------------------------------------------------------------------------------------------------------------

    private static final String system = "nacos-root";
    //    private static final String system = scanner("系统名称");
    private static final String pro = "";
    //    private static final String pro = scanner("项目名称");
    private static final String model = "";
    //    private static final String model = scanner("模块名称");
    private static final String project = "gm-demo";
//    private static final String project = scanner("项目名称(单体项目才用这个名字)");

    private static final String rootPackage = "cn.huolala" + ("".equals(system) ? ""
            : "." + system.replaceAll("-", "."));
//    private static final String rootPackage = scanner("根路径(包)");

    private static final String parentPackage = "".equals(model)
            ? rootPackage + "." + project.replace("-", ".")
            : rootPackage + "." + model.replace("-", ".");

    private static final String tablePrefix = "t_pos_";
//    private static final String tablePrefix = scanner("表名前缀(生成文件将去除)");

    // -----------------------------------------------------------------------------------------------------------------

    private static final String url = "jdbc:mysql://127.0.0.1:3306/db0?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "root";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 防止敏感信息泄露 -> 请手动调整配置
     *
     * @param args thank you, my friend !
     */
    public static void main(String[] args) {
        // 生成指定表
        String table = "file_business (不带前缀的表名)";
        while (!StringUtils.isEmpty(table = scanner("表名"))) {
            // 代码生成器
            AutoGenerator mpg = mpg();
            mpg.getStrategy().setInclude(table);
            // 执行生成器
            mpg.execute();
        }
    }


    private static AutoGenerator mpg() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = getGlobalConfig();
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = getDataSourceConfig();
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = getPackageConfig();
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = getInjectionConfig();
        mpg.setCfg(cfg);

        // 自定义模板
        TemplateConfig templateConfig = getTemplateConfig();
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = getStrategyConfig();
        mpg.setStrategy(strategy);

        return mpg;
    }

    private static StrategyConfig getStrategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段 (父类有的话, 实体类就没有咯)
//        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(tablePrefix);
        // 逻辑删除字段名 (需要配置全局数据库逻辑删除标识值: v3.0.1之后支持)
//        strategy.setLogicDeleteFieldName("del");
        return strategy;
    }

    private static TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setService("templates/customService.java");
        templateConfig.setServiceImpl("templates/customServiceImpl.java");
        templateConfig.setController("templates/customController.java");
        return templateConfig;
    }

    private static InjectionConfig getInjectionConfig() {
        String projectPath = System.getProperty("user.dir");
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // 在配置中使用 cfg.Vo 获取参数
                Map<String, Object> map = new HashMap();
                map.put("Vo", parentPackage + "." + "controller.vo");
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        String voTemplatePath = "/templates/customVo.java.ftl";
        focList.add(new FileOutConfig(voTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/" + system + "/" + pro + "/" + model + "/src/main/java/" + parentPackage.replace(".", "/") + "/controller/vo"
                        + "/" + tableInfo.getEntityName() + "Vo" + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    private static PackageConfig getPackageConfig() {
        PackageConfig pc = new PackageConfig();
        pc.setParent(parentPackage);
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("dao.mapper");
        pc.setEntity("dao.entity");
        pc.setXml("dao.mapper");
        return pc;
    }

    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        // dsc.setSchemaName("public");
        dsc.setUrl(url);
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        return dsc;
    }

    private static GlobalConfig getGlobalConfig() {
        String projectPath = System.getProperty("user.dir");
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/" + system + "/" + pro + "/" + model + "/src/main/java");
        gc.setAuthor("Timi°");
        gc.setOpen(false);
        gc.setServiceName("%sService");
        gc.setMapperName("%sMapper");
        gc.setSwagger2(true);
        gc.setFileOverride(true);
        // 时间类型都用Date, 不用LocalDateTime等
        gc.setDateType(DateType.ONLY_DATE);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        return gc;
    }

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
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}