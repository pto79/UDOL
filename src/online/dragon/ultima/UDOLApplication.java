package online.dragon.ultima;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("service")
public class UDOLApplication extends Application {
	
	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		singletons.add(new UDOLService());
		System.out.println("UDOLService added");
		return singletons;			
	}
	
//    @Override
//    public Set<Class<?>> getClasses() {
//        final Set<Class<?>> classes = new HashSet<Class<?>>();
//        // register root resource
//        classes.add(UDOLService.class);
//        
//        // Add additional features such as support for Multipart.
//        //classes.add(MultiPartFeature.class);
//        System.out.println("UDOLService2 added");
//        return classes;
//    }
}
