package MundoBloco;

public class TBlocos {

    
    public class NoBloco {
    
    private Bloco bloco;
   
    private NoBloco proximoNode;
    
    public NoBloco(int codigoBloco) {        
        this.bloco = new Bloco(codigoBloco);        
    }    
    public NoBloco(int codigoBloco, NoBloco proximo) {        
        this.bloco = new Bloco(codigoBloco);
        this.proximoNode = proximo;        
    }
    
    public Bloco getBloco() {
        return this.bloco;
    }
    
   
    public NoBloco getProximoBloco() {
        return this.proximoNode;
    }
    
    public void setProximoBloco(NoBloco proximo) {
        this.proximoNode = proximo;
    }
}
    // Guarda os nós de cadas lista em um vetor
    public NoBloco[] VetorBlocos;
    
    // Guarda o ultimo bloco de cada lista do vetor
    public NoBloco[] UltBloco;

    // Recebe a quantidade da lista encadeada.
    public TBlocos(int qtdLista) {
        
        // Instancia o vetor vetorBlocos com tamanho qtdLista
        VetorBlocos = new NoBloco[qtdLista];
        UltBloco = new NoBloco[qtdLista];
        
        // Pecorre cada posição do vetor e insere seu Nó com valor de acordo com a posição
        for(int i = 0; i < qtdLista; i++) {            
            VetorBlocos[i] = new NoBloco(i);
            UltBloco[i] = VetorBlocos[i];
        }       
    }    
    // Metodo que valida se a lista na posição posLista é vazia
    public boolean isEmpty(int posLista) {        
        return VetorBlocos[posLista] == null;   
    }    
    // Retorna uma tupla com a localização do bloco
    // posicao 0: Localização no vetor de lista
    // posicao 1: Locaização na lista encadeada
    private int[] pesquisaBloco(int codigo) {
        
        // pesquisa o bloco a procura de posições vazias
        for(int i = 0; i < VetorBlocos.length; i++) {
            
            if(VetorBlocos[i] != null) {                
                NoBloco aux = VetorBlocos[i];
                int pos = 0;
                 while(aux != null) {
                    if(aux.getBloco().getCodigo() == codigo) {                        
                        return new int[] {i, pos};
                    }
                    pos++;
                    aux = aux.getProximoBloco();
                }
            }
        }
        return null;
    }
    
    //Localiza o objeto baseado em sua posição
    private NoBloco getBloco(int[] pos) {        
        NoBloco atual = VetorBlocos[pos[0]];        
        for(int i = 0; i < pos[1] - 1; i++) {
            atual = atual.getProximoBloco();
        }            
        return atual;
    }
    
    public NoBloco getBloco(int[] pos, int codBloco) {
        
        // Buscar o bloco A e seu conjuto
        if(codBloco == VetorBlocos[pos[0]].getBloco().getCodigo()) {
            return VetorBlocos[pos[0]];
        } 
        else {
            NoBloco aux = VetorBlocos[pos[0]];
            for(int i = 0; i < pos[1]; i++) {
                aux = aux.getProximoBloco();
            }
            return aux;
        }
    }
    
    // Metodo que move o bloco ou a cadeia de blocos
    private boolean moveBlocos(int codBlocoA, int codBlocoB) {
        
        // Se codigo A for diferente de B
        if(codBlocoA != codBlocoB) {            
            // Busca a posição do bloco
            int[] posA = pesquisaBloco(codBlocoA);
            int[] posB = pesquisaBloco(codBlocoB);
            
            // Se não estiverem na mesma lista encadeada
            if(posA[0] != posB[0]) {                
                // Busca a referencia do objeto A e B
                NoBloco blocoA = getBloco(posA, codBlocoA);
                NoBloco blocoB = getBloco(posB, codBlocoB);                
                // Se B estiver na ultima possição
                if(blocoB.getProximoBloco() == null) {
                    // Seta A como o proximo
                    blocoB.setProximoBloco(blocoA);                    
                } 
                else {               
                    // Seta o A na ultima posição da lista que B pertence
                    UltBloco[posB[0]].setProximoBloco(blocoA);                    
                }
                
                // Seta A como o ultimo bloco da lista que pertence B
                UltBloco[posB[0]] = UltBloco[posA[0]];                
                // Se A estava na primeira posição
                if(posA[1] == 0) {                    
                    // Anula o valor na origem e também a referencia da ultima posição
                    VetorBlocos[posA[0]] = null;
                    UltBloco[posA[0]] = null;                   
                } 
                else { 
                    // Encontra o bloco anterior a A na lista de origem
                    NoBloco anteriorA = getBloco(new int[] {posA[0], posA[1]});                    
                    // Seta a posição anterior de A com valor nulo
                    anteriorA.setProximoBloco(null);                  
                    UltBloco[posA[0]] = anteriorA;                    
                }                
                return true;
            }
        }        
        return false;        
    }
    
    private void retornarBlocos(int codigo) {       
        // Pesquisa a posição do bloco
        int[] pos = pesquisaBloco(codigo);        
        // Se o bloco estive na posição inicial
        if(pos[0] == codigo) {            
            // Se for a primeira posição
            if(pos[1] == 0) {                
                //Pecorre todos elementos acima dele e retorna para a sua posição de origem
                NoBloco aux = VetorBlocos[codigo].getProximoBloco();               
                while(aux != null) {                   
                    VetorBlocos[aux.getBloco().getCodigo()] = aux;
                    aux = aux.getProximoBloco();
                }               
                VetorBlocos[pos[0]].setProximoBloco(null);
            }
        } 
        else {            
            // Pecorre a lista até encontrar o bloco anterior ao bloco buscado
            NoBloco blocoAnterior = VetorBlocos[pos[0]];           
            for(int i = 0; i < pos[1] - 1; i++) {               
                blocoAnterior = blocoAnterior.getProximoBloco();               
            } 
            NoBloco bloco = blocoAnterior.getProximoBloco();            
            // Seta o valor nulo a posição que estava o bloco
            blocoAnterior.setProximoBloco(null);            
            // Se tiver mais elemento depois do bloco
            while(bloco != null) {                
                // Retorna para posição original
                VetorBlocos[bloco.getBloco().getCodigo()] = bloco;               
                blocoAnterior = bloco;
                bloco = bloco.getProximoBloco();
                blocoAnterior.setProximoBloco(null);
            }           
        }
    }    
    // Retona as listas em formato de texto
    public String getListas() {
        
        String saida = " ";
        
        for(int i = 0; i < VetorBlocos.length; i++) {            
            saida += i + ": ";            
            NoBloco atual = VetorBlocos[i];            
            while(atual != null) {                
                saida += atual.getBloco().getCodigo() + " ";
                atual = atual.getProximoBloco();                                
            }            
            saida += "\n";
        }        
        return saida;
    }    
    // move a onto b
    public boolean moveAOntoB(int l, int c) {        
        retornarBlocos(l);
        retornarBlocos(c);        
        // Empila os blocos
        return moveBlocos(l, c);
    }    
    //move 2 over 0
    public boolean moveAOverB(int l, int c) {        
        retornarBlocos(l);        
        // Empila os blocos em A emcima de B
        return moveBlocos(l, c);
    }    
    // pile a onto b    
    public boolean pileAOntoB(int l, int c) {        
        retornarBlocos(c);        
        // Empila os blocos em A em cima de B
        return moveBlocos(l, c);
    }    
    // pile a over b    
    public boolean pileAOverB(int l, int c) {        
        // Empilha todos blocos em A em cima de B
        return moveBlocos(l, c);
    }    
}
