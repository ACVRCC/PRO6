package projecto4.grupo1.albertoricardo;

import java.util.Set;

import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("/rest")
public class ApplicationConfig extends Application{

	@Override
	public Set<Class<?>> getClasses() {
	    Set<Class<?>> resources = new java.util.HashSet<>();
	    addRestResourceClasses(resources);
	    // extra.. for uploading files via form !
	    //resources.add(MultiPartFeature.class);
	    return resources;
	}
	

	/*
	 * Responsible for adding our "service" classes
	 */
	private void addRestResourceClasses(Set<Class<?>> resources) {
	    // resources.add(xpto.class);
		resources.add(SimpleService.class);
		resources.add(SimpleUserService.class);
		resources.add(SimplePlaylistService.class);
		resources.add(SimpleMusicService.class);
	}
	
}
