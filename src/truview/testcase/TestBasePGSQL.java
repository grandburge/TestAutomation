package truview.testcase;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.testng.annotations.DataProvider;

import com.test.util.ConnectPGSQL;

public class TestBasePGSQL {
	@DataProvider
	public Object[][] providerMethod(Method method)
	{
		String tableName = this.getClass().getSimpleName();
		String methodName = method.getName();						
		String sql = "select * from "+tableName+"-"+methodName;
		List<HashMap<String,String>> result = ConnectPGSQL.query(sql);
		Object[][] testDates = new Object[result.size()][];
		for (int i=0;i<result.size();i++)
		{
			Map<String,String> testDate = result.get(i);
			testDates[i] = new Object[]{testDate};
		}
		return testDates;		
	}
}
