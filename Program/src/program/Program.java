package program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 * @author vitorg
 */
public class Program {

    
    public static void rec(String direc, String palavraChave){
        File dir;
        File dirfiles[];
        
        dir = new File(direc);
        dirfiles = dir.listFiles();
        
        try{
            if(dirfiles.length > 0){
                for(int i=0; i<dirfiles.length; i++){
                    if(dirfiles[i].isFile()){
                        
                        int flag = 0;
                        try{
                            FileReader freader = new FileReader(dirfiles[i]);
                            BufferedReader breader = new BufferedReader(freader);

                            String linha = breader.readLine();
                            while(linha!=null){
                                
                                linha=linha.replace(",", ";");
                                linha=linha.replace(" ", ";");
                                linha=linha.replace(".", ";");
                                linha=linha.replace("!", ";");
                                linha=linha.replace("?", ";");
                               
                                String s[] = linha.split(";");
                                
                                for(int cont=0; cont<s.length; cont++){
                                    if(s[cont].equals(palavraChave))
                                        flag = 1;
                                }
                                
                                
                                linha=breader.readLine();
                            }
                        }
                        catch(Exception e){ 
                            System.out.println("Problemas: "+e.toString()); 
                        }
                        
                        if(flag == 1)
                            System.out.println(dirfiles[i].getAbsolutePath());
                                            
                    }
                    if(dirfiles[i].isDirectory())
                        rec(dirfiles[i].getAbsolutePath(), palavraChave); 
                }
            }
        }
        catch(Exception e){
            System.out.println("ERRO:"+e.toString());
        }
    }
    
    
    public static void main(String[] args) {
        
        //Nos testes sempre usei /home/vitorg/Documentos    ou    /home/vitorg/NetBeansProjects
        
        JOptionPane.showMessageDialog(null, "PROGRAMA DE BUSCA DE PALAVRAS-CHAVE EM PASTAS");
        String palavraChave = JOptionPane.showInputDialog("Palavra-chave que deseja buscar: ");
        String pastaInicial = JOptionPane.showInputDialog("Caminho da pasta em que deseja realizar a busca: ");
        rec(pastaInicial, palavraChave);
    }
    
}
