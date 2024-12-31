package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    //DataProvider 1

    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException{
        String path = ".\\testData\\Login Data.xlsx"; //taking xl file testData

        ExcelUtils xlutil = new ExcelUtils(path);
        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1",1);
        String logindata[][] = new String[totalrows][totalcols]; //created for two dimension array which can stored

        for(int i = 1; i <= totalrows; i++){ // 1 // read the data from xl storing in two dimension array
            for(int j = 0; j < totalcols; j++){ // 0   i is rows j is col
                logindata[i-1][j] = xlutil.getCellData("Sheet1",i,j); //1,0
            }
        }
        return logindata; //returning two dimension array
    }
}
