package framework.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportFactory {

    private static final ExtentReports extentReports = new ExtentReports();
    private static final SparkDate sparkDate = new SparkDate();

    public synchronized static ExtentReports getExtentReports() {


        ExtentSparkReporter reporter = new ExtentSparkReporter("./target/Spark/" + sparkDate.getFormat() + "/index.html");
        reporter.config().setReportName("Dfs Test Report");
        reporter.config().setOfflineMode(true);
        extentReports.attachReporter(reporter);
        return extentReports;
    }

}