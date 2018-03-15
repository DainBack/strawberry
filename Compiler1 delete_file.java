import java.io.File;
import java.nio.file.Path;
public class delete_file {
	public static void main(String agrs[]) {
		String paths = MainClass.getPaths();
		File file = new File(paths);

	if( file.exists() ){
        if(file.delete()){
            System.out.println("File delete Success");
        }else{
            System.out.println("File delete Faile");
        }
    }else{
        System.out.println("File does not exist");
    }


	}
}