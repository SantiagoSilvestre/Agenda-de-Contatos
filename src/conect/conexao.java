package conect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class conexao{
    String sql = "select * from Contatos";
    final private String driver ="net.ucanaccess.jdbc.UcanaccessDriver";
    final private String usuario="";
    final private String senha="";
    private Connection conexao;
    public Statement statement ;
    public ResultSet resultset;
    String url="jdbc:ucanaccess://C:/Users/Santiago/Documents/Contatos.accdb";
    
    
    public boolean conecta(){
        boolean result = true;
        try
        {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,usuario , senha );
            JOptionPane.showMessageDialog(null, "Banco de Dados Conectado!");
        }
        catch (ClassNotFoundException Driver){
            JOptionPane.showMessageDialog(null,"Drive nao localizado : "+Driver );
            result=false;
        }
        catch(SQLException Fonte)
        {
            JOptionPane.showMessageDialog(null,"Erro na Conexao\n"+
                    "\ncom a fonte de dados : \n"+Fonte);
            result=false;
        }
        return (result);
    }
    public void desconecta(){
        boolean result = false;
        
        try
        {
            conexao.close();
            JOptionPane.showMessageDialog(null,"Banco fechado... Desconectou");
        }
        catch(SQLException erroSQL){
            JOptionPane.showMessageDialog(null,"Nao foi possivel"+ 
                    "fechar o banco de dados: "+ erroSQL.getMessage());
            result = false;
        }
    }
    
    public void executeSQL(String sql){
        try{
            statement = conexao.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
        }
        catch(SQLException sqlex){
            JOptionPane.showMessageDialog(null, "Não foi possivel "+
                    "Executar o comando SQL: "+sqlex+",O SQL passado foi "+sql);
        }
    }
    
}
