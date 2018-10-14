package java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

/**
 * @program: demo
 * @description: 客户端
 * @author: bang.chen
 * @create: 2018-10-13 16:54
 **/
public class Client {

    public static void main(String[] args) {
        List<Artist> artistList = initArtistData();
//        calc1(artistList);
//        calc2(artistList);
//        calc3(artistList);
//        calc4(artistList);
//        flatMapTest();
//        streamMin();
        System.out.println(addUp(Stream.of(1,2,3)));
    }


    private static void calc1(List<Artist> artistList) {
        int count = 0;
        for (Artist artist : artistList) {
            if (artist.isFrom("London")) {
                count++;
            }
        }
        System.out.println("clac1 count : " + count);
    }

    private static void calc2(List<Artist> artistList) {
        int count =0;
        Iterator<Artist>  iterator = artistList.iterator();
        while (iterator.hasNext()){
            Artist artist = iterator.next();
            if(artist.isFrom("London")){
                count++;
            }
        }
        System.out.println("clac2 count : " + count);
    }

    private static void calc3(List<Artist> artistList) {
        int count = (int) artistList.stream().filter(artist -> artist.isFrom("London")).count();
        System.out.println("clac3 count : "+count);
    }


    private static void calc4(List<Artist> artistList) {
        long count = artistList.stream().filter(artist -> {
            System.out.println(artist.getName());
            return artist.isFrom("London");
        }).count();
        System.out.println("clac4 count : "+count);

    }


    private static void flatMapTest(){
        List<Integer> together = Stream.of(Arrays.asList(1,2),Arrays.asList(3,4)).flatMap(numbers -> numbers.stream()).collect(Collectors.toList());
        assertEquals(Arrays.asList(1,2,3,4),together);
    }

    private static void streamMin(){
          List<Track> tracks = Arrays.asList(new Track("111",10),new Track("222",5),new Track("333",15));
          Track track = tracks.stream().min(Comparator.comparing(trackParam -> trackParam.getLength())).get();
          assertEquals(tracks.get(1), track);
    }


    /**
     * 编写一个求和函数，计算流中所有数之和
     * @param numbers
     * @return
     */
    private static int  addUp(Stream<Integer> numbers){
        return   numbers.reduce(0,(acc,element) -> acc+element);
    }


    /**
     * 编写一个函数，接受艺术家列表作为参数，返回一个字符串列表，其中包含艺术家的 姓名和国籍;
     * @param artists
     * @return
     */
    public static List<String> getNamesAndOrigins(List<Artist> artists){
        return artists.stream().flatMap(artist -> Stream.of(artist.getName(),artist.getNationality())).collect(toList());
    }

    /**
     * 编写一个函数，接受专辑列表作为参数，返回一个由最多包含 3 首歌曲的专辑组成的 列表。
     * @param albums
     * @return
     */
    public static List<Album> getAlbumsWithAtMostThreeTrack(List<Album> albums){
            return  albums.stream().filter(album -> album.getTrackList().size()<=3).collect(toList());
    }

    /**
     * 迭代。修改如下代码，将外部迭代转换成内部迭代:
     * int totalMembers = 0;
     * for (Artist artist : artists) {
     *          Stream<Artist> members = artist.getMembers();
     *          totalMembers += members.count();
     *      }
     *
     */
    public static int countBandMembersInternal(List<Artist> artists){
        return artists.stream().map(artist -> artist.getMembers().count()).reduce(0L,Long::sum).intValue();
    }





    private static List<Artist> initArtistData() {
        List<Artist> artistList = new ArrayList<>();
        return artistList;
    }



}
