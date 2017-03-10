package com.connectto.wallet.merchant.web.util;

import com.connectto.wallet.merchant.common.util.SetupInfo;
import com.connectto.wallet.merchant.common.util.Utils;
import org.apache.log4j.Logger;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;


public class Initializer implements ServletContextListener {

    /**
     * application context object (container)
     */
    private static ApplicationContext applicationContext;

    private static Logger logger = Logger.getLogger(Initializer.class);

    private static SetupInfo setupInfo;

    /**
     *
     */

    public static String MERCHANT_DATA_FOLDER = "merchantData";

    public static ServletContext context;
    private static String contextPath;
    private static String dataPath;

    private static final String PROD = "prod";
    private static final String DEV = "dev";

    //private static final String RECAPTCHA_SECRET_KEY = "6Ldf0hETAAAAAP94esg5vk5ch2K19_0khG6HFw8v";
    //private static final String RECAPTCHA_CLIENT_KEY = "6Ldf0hETAAAAAKRPDJYZ-rnVHGI8bQyWPeyN7eiM";


    private final static String VELOCITY_INPUT_ENCODING = "UTF-8";
    private final static String VELOCITY_OUTPUT_ENCODING = "UTF-8";

    public final static long NOTIFICATION_DURATION_SECOND = 300;

    public Initializer() {
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {

        try {
            logger.info("-- start application -- ");
            System.setProperty("file.encoding", "UTF-8");

            context = event.getServletContext();

            applicationContext = (ApplicationContext) context.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
            setupInfo = (SetupInfo) applicationContext.getBean("setupInfo");

            //context real path
            contextPath = context.getRealPath("/");
            if (setupInfo.getSetup().equals(DEV)) {

                String[] contextPathDirs = contextPath.split("\\\\");

                /*StringBuffer contextPathBuffer = new StringBuffer();
                for (int i = 0; i < contextPathDirs.length - 1; i++) {
                    String dir = contextPathDirs[i];
                    contextPathBuffer.append(dir).append(File.separator);
                }*/

                //contextPath = contextPathBuffer.toString();
                logger.info("**************************************************************************************************");
                logger.info("contextPath=>" + contextPath);
                logger.info("**************************************************************************************************");
                //images real path
                if (!Utils.isEmpty(setupInfo.getStaticDir())) {
                    dataPath = contextPath + File.separator + MERCHANT_DATA_FOLDER;
                } else {
                    dataPath = setupInfo.getStaticDir();
                }
            } else if (setupInfo.getSetup().equals(PROD)) {

                //images real path
                dataPath = setupInfo.getStaticDirProd() + File.separator + MERCHANT_DATA_FOLDER;

                //images context path
            } else {
                throw new RuntimeException("application init fail " + setupInfo.getSetup());
            }
            logger.info(String.format("Application %s intitilize params [imagePath:%s]", setupInfo.getVersion(), dataPath));
            //init data folder
            initFolders(dataPath);


            //set tmp dir
            File tempDir = (File) context.getAttribute("javax.servlet.context.tempdir");
            ImageIO.setCacheDirectory(tempDir);

            initVelocity();

            logger.info("-- application started -- ");
        } catch (Exception e) {
            throw new RuntimeException("unable intitilize application");
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent event) {
        deRegistering();
    }

    private void  deRegistering(){
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.info(String.format("DeRegistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                logger.error(String.format("Error deRegistering driver %s", driver), e);
            }
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void initVelocity() {
        try {
            //classpath config
            //Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            //Velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

            //file config
            /*String vmPath = context.getRealPath("template");
            Velocity.setProperty("resource.loader","file");
            Velocity.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
            Velocity.setProperty("file.resource.loader.path", vmPath);
            Velocity.setProperty("file.resource.loader.cache", "false");
            Velocity.setProperty("file.resource.loader.modificationCheckInterval", "0");*/

            //webapp config

            Velocity.setProperty("resource.loader","webapp");
            Velocity.setProperty("webapp.resource.loader.class", "org.apache.velocity.tools.view.WebappResourceLoader");
            Velocity.setProperty("webapp.resource.loader.path", "/WEB-INF/webapptemplate/");
            Velocity.setApplicationAttribute("javax.servlet.ServletContext", context);

            //disable template file cache
            //Velocity.setProperty(RuntimeConstants.VM_LIBRARY_AUTORELOAD, true);
            //Velocity.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_CACHE, false);
            //Velocity.setProperty(RuntimeConstants.VM_PERM_ALLOW_INLINE_REPLACE_GLOBAL, true);

            Velocity.setProperty(RuntimeConstants.INPUT_ENCODING, VELOCITY_INPUT_ENCODING);
            Velocity.setProperty(RuntimeConstants.OUTPUT_ENCODING, VELOCITY_OUTPUT_ENCODING);

            //VelocityLogger vLogger = new VelocityLogger(this.getClass());
            //Velocity.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM, vLogger);
            Velocity.setProperty( RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
                    "org.apache.velocity.runtime.log.Log4JLogChute" );

            Velocity.setProperty("runtime.log.logsystem.log4j.logger", "VLOG");

            Velocity.init();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public static SetupInfo getSetupInfo() {
        return setupInfo;
    }

    public static String getUploadDir() {
        return dataPath  ;
    }

    private void initFolders(String contextRealPath) {
        File imageFolder = new File(contextRealPath);
        if (!imageFolder.exists()) {
            boolean created = imageFolder.mkdirs();
            if (created)
                logger.info(String.format("%s folder created successfully", contextRealPath));
            else
                throw new RuntimeException(String.format("Unable to create posts folder[%s]", contextRealPath));
        } else
            logger.info(String.format("%s folder already exist", contextRealPath));
    }

    public static boolean initFolders(String contextRealPath, String... folders) {

        try {
            for (String folder : folders) {
                String path = contextRealPath + File.separator + folder;
                File imageFolder = new File(path);
                if (!imageFolder.exists()) {
                    boolean created = imageFolder.mkdirs();
                    if (created)
                        logger.info(String.format("%s folder created successfully", path));
                    else {
                        logger.info(String.format("Unable to create folder[%s]", path));
                        return false;
                        //throw new InternalErrorException(String.format("Unable to create folder[%s]", path));
                    }
                } else
                    logger.info(String.format("%s folder already exist", path));
            }
        } catch (Exception e) {
            logger.error(e);
            return false;
        }

        return true;
    }

    public static String getWalletTransactionUploadDir() {
        return dataPath ;//+ File.separator + WALLET_UPLOAD_FOLDER + File.separator + WALLET_TRANSACTION_FOLDER;
    }

    @Deprecated
    public static String getAgreementDocumentUploadDir() {
        String staticDirProd = "/opt/tomcat/webapps";
        String AGREEMENT_DOCUMENT_FOLDER = "agreementDocument";
        String dataPath = staticDirProd + File.separator + MERCHANT_DATA_FOLDER;
        return dataPath + File.separator + AGREEMENT_DOCUMENT_FOLDER;
    }

    @Deprecated
    public static String getCompanyDocumentUploadDir() {
        String staticDirProd = "/opt/tomcat/webapps";
        String AGREEMENT_DOCUMENT_FOLDER = "company";
        String dataPath = staticDirProd + File.separator + MERCHANT_DATA_FOLDER;
        return dataPath + File.separator + AGREEMENT_DOCUMENT_FOLDER;
    }

}
