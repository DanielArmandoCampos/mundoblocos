/*
Trabalho de AED2

Danie Armando Campos

*/

package MundoBloco;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class MundoBlocos {

    public static void main(String[] args) {        
        // Caminho relativo dos arquivos de entrada e saida
        String entrada = "src\\dados\\arquivo1.txt";
        String saida = "src\\dados\\saida.txt";        
        
        try {            
            // Realiza a leitura do arquivo de entrada
            FileReader arquivo = new FileReader(entrada);
            BufferedReader leitura = new BufferedReader(arquivo);            
            String X = leitura.readLine();            
            int qtdBlocos = Integer.parseInt(X);            
            TBlocos tBlocos = new TBlocos(qtdBlocos);
            
            // Enquanto diferente de quit
            while(!X.equals("quit")) {
                
                String[] separarLinha = X.split(" ");                
                //Caso o movimento seja "move" faça
                if(separarLinha[0].equals("move")) {
                    
                    int l = Integer.parseInt(separarLinha[1]);
                    int c = Integer.parseInt(separarLinha[3]);                    
                    //Caso o comdando seja "onto" faça
                    if(separarLinha[2].equals("onto")) {                        
                        tBlocos.moveAOntoB(l,c);                        
                    }

                    //Caso o comdando seja "over" faça
                    else if(separarLinha[2].equals("over")) {                         
                        tBlocos.moveAOverB(l, c);                        
                    }                    
                } 
                //Caso o movimento seja "pile" faça
                else if(separarLinha[0].equals("pile")) {     
                    
                    int l = Integer.parseInt(separarLinha[1]);
                    int c = Integer.parseInt(separarLinha[3]);
                    //Caso o comdando seja "onto" faça
                    if(separarLinha[2].equals("onto")) {                         
                        tBlocos.pileAOntoB(l, c);                    
                    } 
                    //Caso o comdando seja "over" faça
                    else if(separarLinha[2].equals("over")) {                          
                        tBlocos.pileAOverB(l, c);                        
                    }
                }
                X = leitura.readLine();                
            }            
            // Feche o arquivo
            leitura.close();
            arquivo.close();            
            String saidaTexto = tBlocos.getListas();
            
            // Escrever no arquivo
            FileWriter arquivoSaida = new FileWriter(saida);
            BufferedWriter escreve = new BufferedWriter(arquivoSaida);
            
            // Escreve a lista
            escreve.append(saidaTexto);
            
            // Fecha o arquivo
            escreve.close();
            arquivoSaida.close();
            
            // Tratamento de excessoes
        } catch (IOException e) {            
            System.err.printf("Falha ao manipular o arquivo: ", e.getMessage());            
        }
    }        
}
