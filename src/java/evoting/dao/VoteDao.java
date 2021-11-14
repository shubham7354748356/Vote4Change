/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateInfo;
import evoting.dto.VoteDto;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author india
 */
public class VoteDao 
{
    private static PreparedStatement ps1 , ps2 , ps3 ,ps4 ,ps5,ps6;
    private static Statement st;
    static
    {
        try
        {
            ps1 = DBConnection.getConnection().prepareStatement("select candidate_id from voting where voter_id=?");
            ps2 = DBConnection.getConnection().prepareStatement("select candidate_id , username , party , symbol from candidate , user_details where candidate.user_id=user_details.adhar_no and candidate.candidate_id=?");
            ps3 = DBConnection.getConnection().prepareStatement("insert into voting values(?,?)");
            ps4 = DBConnection.getConnection().prepareStatement("select party, count(*)  from candidate ,  voting where candidate.candidate_id=voting.candidate_id group by party order by 2 desc");
            ps5 = DBConnection.getConnection().prepareStatement("select voter_id from voting , candidate where candidate.candidate_id=voting.candidate_id and candidate.party=?");
            st = DBConnection.getConnection().createStatement();
            ps6 = DBConnection.getConnection().prepareStatement("delete from voting where candidate_id=(select candidate_id from candidate where user_id=?)");
        }
        catch(SQLException sq)
        {
            sq.printStackTrace();
        }
    }
public static String getCandidateId(String userId)throws SQLException
{
    ps1.setString(1, userId);
    ResultSet rs = ps1.executeQuery();
    if(rs.next())
    {
        return rs.getString(1);
    }
    return null;
}
public static CandidateInfo getVote(String candidateId)throws Exception
{
    ps2.setString(1, candidateId);
    ResultSet rs = ps2.executeQuery();
    CandidateInfo ci = new CandidateInfo();
    Blob blob;
    InputStream inputstream;
    int read;
    byte [] buffer;
    byte [] imageBytes;
    ByteArrayOutputStream outputstream;
    if(rs.next())
    {
        blob = rs.getBlob(4);
        inputstream = blob.getBinaryStream();
        read = -1;
        outputstream = new ByteArrayOutputStream();
        buffer = new byte[4096];
        while((read=inputstream.read(buffer))!=-1)
        {
            outputstream.write(buffer, 0, read);
        }
        imageBytes = outputstream.toByteArray();
        Base64.Encoder en = Base64.getEncoder();
        String image = en.encodeToString(imageBytes);
        ci.setCandidateId(candidateId);
        ci.setCandidateName(rs.getString(2));
        ci.setParty(rs.getString(3));
        ci.setSymbol(image);
    }
    return ci;
    
}
public static boolean addVote(VoteDto obj)throws SQLException
{
    ps3.setString(1, obj.getCandidateid());
    ps3.setString(2, obj.getUserid());
    return ps3.executeUpdate()==1;
}
public static Map<String  , Integer>getResult()throws SQLException
    {
        Map<String , Integer> result = new LinkedHashMap<>();
        ResultSet rs = ps4.executeQuery();
        System.out.println("query executed in the getresult");
        while(rs.next())
        {
            System.out.println("we got party=>"+rs.getString(1)+" vote =>"+rs.getString(2));
            System.out.println(1);
            result.put(rs.getString(1), rs.getInt(2));
        }
        return result;
    }
public static int getVoteCount()throws SQLException
{
    ResultSet rs = st.executeQuery("select count(*) from voting");
    if(rs.next())
    {
        return rs.getInt(1);
    }
    return 0;
}

public static float getMaleVoteCount(String party)throws SQLException
{
    System.out.println("we are in the get male vote count");
    ps5.setString(1, party);
    ResultSet rs = ps5.executeQuery();
    System.out.println("fetched result set");
    int male=0;
   
    while(rs.next())
    {
        PreparedStatement temp = DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=? and gender='male'");
        System.out.println("created temp for "+rs.getString(1));
        temp.setString(1, rs.getString(1));
        ResultSet rs1 = temp.executeQuery();
        System.out.println("rs2 is fetched");
        if(rs1.next())
            male++;
        System.out.println("male => "+male);
    }
    return male;
}
public static boolean deleteCandidate(String u)throws SQLException
{
    PreparedStatement temp = DBConnection.getConnection().prepareStatement("select * from voting where candidate_id=(select candidate_id from candidate where user_id=?)");
    temp.setString(1, u);
    if(temp.executeQuery().next())
    {
        System.out.println("in vote dao we are in the if");
    ps6.setString(1, u);
    return ps6.executeUpdate()!=0;
    }
    System.out.println("we are returning true from vote dao");
    return true;
}
}