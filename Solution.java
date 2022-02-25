class Solution {
    Map<String,Boolean> map ;
    public boolean isScramble(String s1, String s2) {
        map = new HashMap<String,Boolean>();
        boolean result = isScrambled(s1,s2);
        return result;
    }
    
    public boolean isScrambled(String str,String s){
        //System.out.println("came to call str "+str+" s "+s);
        if(map.containsKey(str+"  "+s))
            return map.get(str+"  "+s);
        int beg=0;
        int end = str.length()-1;
        if(str.length()!=s.length())
            return false;
        if(beg==end){
            if(str.charAt(beg)==s.charAt(beg))
                return true;
            else 
                return false;
        }
        boolean lResult=false;
        boolean rResult=false;
        boolean result = false;
        for(int k = beg+1; k<= end;k++){
            String lS = s.substring(beg,k);
            String rS = s.substring(k,end+1);
            //check if smaller substring is having all characters same as some contiguous part of original String
            boolean leftMatch=false;
            boolean rightMatch = false;
                //compare from left part of original
                 leftMatch = containsCharacters(str,beg,k-1,lS) && containsCharacters(str,k,end,rS);
                 rightMatch = containsCharacters(str,end-k+beg+1,end,lS) && containsCharacters(str,beg,end-k+beg,rS);
          
            //System.out.println("Str "+str+" s "+s+" k = "+k+" ls "+lS+" rs "+rS+" leftMatch "+leftMatch+" rightMatch "+rightMatch);
            if(!leftMatch && !rightMatch)
                continue;
            if(leftMatch){
                //System.out.println("Calling str "+str.substring(beg,k)+" s "+ lS);
                lResult = isScrambled(str.substring(beg,k),lS);
                map.put(str.substring(beg,k)+"  "+lS,lResult);
                //System.out.println("lResult "+lResult);
                if(lResult){
                    //System.out.println("Calling str "+str.substring(k,end+1)+" s "+ rS);
                    rResult = isScrambled(str.substring(k,end+1),rS);
                    map.put(str.substring(k,end+1)+"  "+rS,rResult);
                    //System.out.println("rResult "+rResult);
                }
                result = lResult&rResult;
                if(result)
                    break;
            }
            if(rightMatch){
                //System.out.println("Calling str "+str.substring(end-k+beg+1,end+1)+" s "+ lS);
                lResult = isScrambled(str.substring(end-k+beg+1,end+1),lS);
                map.put(str.substring(end-k+beg+1,end+1) +"  "+lS,lResult);
                //System.out.println("lResult "+lResult);
                if(lResult){
                    //System.out.println("Calling str "+str.substring(beg,end-k+beg+1)+" s "+ rS);
                    rResult = isScrambled(str.substring(beg,end-k+beg+1),rS);
                    map.put(str.substring(beg,end-k+beg+1)+"  "+rS,rResult);
                }
                result=lResult&rResult;
                if(result)
                    break;
            }
            //System.out.println("Result at end of k "+k+" "+result);
        }
        return result;
    }
    
    public boolean containsCharacters(String str, int beg, int end, String s){
         for(int i =beg; i <=end; i++){
			String temp = str.substring(i,i+1);
			int idx = s.indexOf(temp);
            if(idx!=-1){
                s=s.substring(0,idx)+s.substring(idx+1,s.length());
			}else
				return false;
        }
        return true;
    }
        
}
