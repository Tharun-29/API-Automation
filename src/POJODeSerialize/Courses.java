package POJODeSerialize;

import java.util.List;

public class Courses {
         private List<webAutomation> webAutomation;
         private List<api> api;
         private List<mobile> mobile;
         
         public List<POJODeSerialize.webAutomation> getWebAutomation() {
			return webAutomation;
		}
		public void setWebAutomation(List<POJODeSerialize.webAutomation> webAutomation) {
			this.webAutomation = webAutomation;
		}
		public List<POJODeSerialize.mobile> getMobile() {
			return mobile;
		}
		public void setMobile(List<POJODeSerialize.mobile> mobile) {
			this.mobile = mobile;
		}
		public List<POJODeSerialize.api> getApi() {
			return api;
		}
		public void setApi(List<POJODeSerialize.api> api) {
			this.api = api;
		}
		
         
         
}
