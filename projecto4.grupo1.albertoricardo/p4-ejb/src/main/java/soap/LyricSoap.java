package soap;

import com.chartlyrics.api.Apiv1;
import com.chartlyrics.api.Apiv1Soap;

public class LyricSoap {

	public static void main(String[] args) {
		
		Apiv1 api = new Apiv1();
		Apiv1Soap soap = api.getApiv1Soap();
		while(true) {
			try{
				Thread.sleep(2*1000);
		
		
		System.out.println(soap.searchLyricDirect("Abba", "Fernando").getLyric());
		break;
			} catch (Exception e) {
			System.out.println("---");
		}
		}		
	}

}
