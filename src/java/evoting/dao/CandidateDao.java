/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateDTO;
import evoting.dto.CandidateDetails;
import evoting.dto.CandidateInfo;
import evoting.dto.UpdateDto;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

/**
 *
 * @author india
 */
public class CandidateDao 
{
    private static PreparedStatement ps , ps1 , ps2 , ps3 , ps4 , ps5,ps6 , ps7,ps8,ps9,ps10 , ps11;
    private static Statement st;
    static
    {
        try
        {
            ps = DBConnection.getConnection().prepareStatement("select count(*) from candidate");
            ps1 = DBConnection.getConnection().prepareStatement("select username from user_details where adhar_no=?");
            ps2 = DBConnection.getConnection().prepareStatement("select distinct city from user_details");
            ps3 = DBConnection.getConnection().prepareStatement("insert into candidate values(?,?,?,?,?)");
            ps4 = DBConnection.getConnection().prepareStatement("select * from candidate where candidate_id=?");
            ps5 = DBConnection.getConnection().prepareStatement("select candidate_id , username , party , symbol from candidate , user_details where candidate.user_id = user_details.adhar_no and candidate.city=(select city from user_details where adhar_no=?)");
            ps6 = DBConnection.getConnection().prepareStatement("update candidate set party=?,city=?,symbol=? where candidate_id=?");
            st = DBConnection.getConnection().createStatement();
            ps7 = DBConnection.getConnection().prepareStatement("delete from candidate where candidate_id=?");
            ps8 = DBConnection.getConnection().prepareStatement("select * from candidate where user_id=?");
            ps9 = DBConnection.getConnection().prepareStatement("select * from candidate where city=? and party=?");
            ps10 = DBConnection.getConnection().prepareStatement("delete from candidate where user_id=?");
            ps11 = DBConnection.getConnection().prepareStatement("select symbol from candidate where party=?");
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static String getUserNameById(String uid)throws SQLException
    {
        ps1.setString(1 , uid);
        ResultSet rs = ps1.executeQuery();
        if(rs.next())
        {
            return rs.getString(1);
        }
        else
        {
            return null;
        }
    }
    public static ArrayList<String > getCity()throws SQLException
    {
        ArrayList<String > cityList = new ArrayList<>();
        ResultSet rs = ps2.executeQuery();
        while(rs.next())
        {
            cityList.add(rs.getString(1));
        }
        return cityList;
    }
    public static String getId()throws SQLException
    {
        ResultSet rs =  ps.executeQuery();
        if(rs.next())
        {
            return "C-"+(100+rs.getInt(1)+1);
        }
        return "C-"+(100+rs.getInt(1)+1);
    }
    public static boolean addCandidate(CandidateDTO obj)throws SQLException
    {
        ps3.setString(1, obj.getCandidateid());
        ps3.setString(2, obj.getParty());
        ps3.setString(5, obj.getCity());
        ps3.setString(3, obj.getUserid());
        ps3.setBlob(4, obj.getSymbol());
        return ps3.executeUpdate()==1;
    }
    public static ArrayList<String> getCandidateId()throws SQLException
    {
        ResultSet rs = st.executeQuery("select candidate_id from candidate");
        ArrayList<String > al  = new ArrayList<>();
        while(rs.next())
        {
            al.add(rs.getString(1));
        }
        return al;
    }
    public static CandidateDetails getDetailsById(String cid)throws Exception
    {
        ps4.setString(1, cid);
       ResultSet rs =  ps4.executeQuery();
       CandidateDetails cd = new CandidateDetails();
       Blob blob;
       InputStream inputstream;
       int read;
       byte buffer[];
       byte imageByte[];
       String base64image;
       ByteArrayOutputStream outputstream;
       if(rs.next())
       {
          
           blob = rs.getBlob(4);
           inputstream = blob.getBinaryStream();
           outputstream = new ByteArrayOutputStream();
           read=-1;
           buffer = new byte[4096];
           while((read=inputstream.read(buffer))!=-1)
           {
               outputstream.write(buffer, 0, read);
           }
           imageByte = outputstream.toByteArray();
           Base64.Encoder en =Base64.getEncoder();
           base64image = en.encodeToString(imageByte);
           cd.setSymbol(base64image);
           cd.setCandidateid(cid);
           cd.setCandidateName(getUserNameById(rs.getString(3)));
           cd.setCity(rs.getString(5));
           cd.setParty(rs.getString(2));
           cd.setUserid(rs.getString(3));
       }
       return cd;
       
        
    }
    
    
    
    public static ArrayList<CandidateInfo> viewCandidate(String adhar)throws Exception
    {
        ArrayList<CandidateInfo> al = new ArrayList<>();
        ps5.setString(1,adhar);                  
        ResultSet rs = ps5.executeQuery();
        Blob blob;
        InputStream inputstream;
        int read;
        ByteArrayOutputStream outputstream;
        byte [] buffer;
        byte [] imageBytes;
        while(rs.next())
        {
             
            blob = rs.getBlob(4);
            inputstream = blob.getBinaryStream();
            buffer = new byte[4096];
            read = -1;
            outputstream = new ByteArrayOutputStream();
            while((read=inputstream.read(buffer))!=-1)
            {
                outputstream.write(buffer, 0, read);
            }
            imageBytes = outputstream.toByteArray();
            Base64.Encoder en = Base64.getEncoder();
            String image = en.encodeToString(imageBytes);
            CandidateInfo ci = new CandidateInfo();
            ci.setSymbol(image);
            ci.setCandidateId(rs.getString(1));
            ci.setCandidateName(rs.getString(2));
            ci.setParty(rs.getString(3));
            al.add(ci);
            
        }
        return al;
        
    }
    public static boolean updateCandidate(UpdateDto ud)throws SQLException
    {
        ps6.setString(1, ud.getParty());
        ps6.setString(2, ud.getCity());
        ps6.setBinaryStream(3, ud.getSymbol());
        ps6.setString(4, ud.getCandidateid());
        return ps6.executeUpdate()==1;
        
    }
    public static boolean deleteCandidate(String candidateid)throws SQLException
    {
        ps7.setString(1, candidateid);
        return ps7.executeUpdate()==1;
    }
    
    
    public static boolean isCandidatePresent(String userid)throws SQLException
    {
        ps8.setString(1, userid);
        return ps8.executeQuery().next();
    }
    public static boolean isPartyPresent(String city , String party)throws SQLException
    {
        ps9.setString(1, city);
        ps9.setString(2, party);
        return ps9.executeQuery().next();
    }
    public static boolean deleteCandidateByUserid(String userid)throws SQLException
    {
         PreparedStatement temp = DBConnection.getConnection().prepareStatement("select * from candidate where user_id=?");
    temp.setString(1, userid);
    if(temp.executeQuery().next())
    {
        System.out.println("we are in the if of the candidate dao");
             ps10.setString(1, userid);
        return ps10.executeUpdate()!=0;
    }
    System.out.println("we are returning true from the candidate Dao");
    return true;
       
    }
    public static String getPartySymbol(String party)throws Exception
    {
        ps11.setString(1, party);
        System.out.println("we are here for getting symbole for =>"+party);
        ResultSet rs  =ps11.executeQuery();
        System.out.println("result set is fetched");
        if(rs.next())
        {
            System.out.println("we are in the if of next");
             Blob blob;
        InputStream inputstream;
        int read;
        ByteArrayOutputStream outputstream;
        byte [] buffer;
        byte [] imageBytes;
          blob = rs.getBlob(1);
          System.out.println(blob==null);
            inputstream = blob.getBinaryStream();
            buffer = new byte[4096];
            read = -1;
            outputstream = new ByteArrayOutputStream();
            while((read=inputstream.read(buffer))!=-1)
            {
                outputstream.write(buffer, 0, read);
            }
            imageBytes = outputstream.toByteArray();
            Base64.Encoder en = Base64.getEncoder();
            String image = en.encodeToString(imageBytes);
            System.out.println("we are returning symbol");
            return "data:image/jpf;base64,"+image;
        }
        return null;
    }
}
