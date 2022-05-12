import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class WebAPI {

    public static void main(String[] args)
    {
        String APIkey = "301995e2246067ea90f17ab07e41cdf6";

        String urlNowPlaying = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + APIkey + "&language=en-US";
        makeAPICall(urlNowPlaying);

        System.out.print("Which movie (enter ID)? ");
        Scanner s = new Scanner(System.in);
        String movieID = s.nextLine();

        String movieURL = "https://api.themoviedb.org/3/movie/" + movieID + "?api_key=" + APIkey + "&language=en-US";
        makeAPICall(movieURL);
    }

    public static void makeAPICall(String url)
    {
        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest.Builder builder = HttpRequest.newBuilder();
            builder.uri(myUri); // sets the URI
            HttpRequest request = builder.build();

            // it is common to see this in one line, like this:
            //HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //System.out.println(response);
            //System.out.println(response.request());
            //System.out.println(response.statusCode());
            //System.out.println(response.headers());
            System.out.println(response.body());

            // FRIDAY
            //parse(response.body());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    public static void parse(String str)
    {
        try {
            JSONObject json = new JSONObject(str);
            JSONArray genreList = json.getJSONArray("results");

            ArrayList<String> genres = new ArrayList<String>();

            for (int i = 0; i < genreList.length(); i++)
            {
                JSONObject genreObj = genreList.getJSONObject(i);
                String genre = genreObj.getString("original_title");
                genres.add(genre);
            }

            System.out.println(genres);
        }

        catch (Exception e) { }


        // WHAT GENRE DO YOU WANT TO SEE ALL MOVIES FOR?

        // MAKE A NEW REQUEST!!!
    }
}