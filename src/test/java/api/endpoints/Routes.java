package api.endpoints;



public class Routes {
	
	//BASE URL
	public static String base_url = "https://petstore.swagger.io/v2" ;
	
	//USER ROUTES
	
	public static String post_url = base_url+"/user" ;
	
	public static String get_url = base_url+"/user/{username}";

	public static String update_url = base_url+"/user/{username}";

	public static String delete_url = base_url+"/user/{username}";
	
	//ADD STORE ROUTES
	
	//ADD PET ROUTES
}
