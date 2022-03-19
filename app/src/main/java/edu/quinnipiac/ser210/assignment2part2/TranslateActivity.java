package edu.quinnipiac.ser210.assignment2part2;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class TranslateActivity extends AsyncTask<Void, Void, String> {

    // The TextView where we will show results
    private TextView mTextView;
    private String mOriginalText;

    private static final String LOG_TAG = " ";

    // Constructor that provides a reference to the TextView from the MainActivity
    public TranslateActivity(String originalText, TextView translatedView) {
        mOriginalText = originalText;
        mTextView = translatedView;
    }

    /**
     * Method for downloading book information from the Books API based on a search term.
     * This method makes a network call so it can not be called on the main thread.
     * @param queryString The search term for the Books API query
     * @return The raw response from the API as a JSON String
     */
    protected String getTranslateText(String queryString){

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String translateJSONString = null;

        // Attempt to query the Translate API.
        try {

            URL requestURL = new URL("https://google-translate20.p.rapidapi.com/translate");
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setDoOutput(true);

            urlConnection.setInstanceFollowRedirects(false);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("X-RapidAPI-Key", "7e234b4b02msh137bc90f6b98f4dp16bb1ejsn740c24890ec4");

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            final StringBuilder requestData = new StringBuilder(100);
            requestData.append("&text=").append(queryString);
            requestData.append("&tl=").append("es"); // TODO: SWITCH THIS LANGUAGE TO ANOTHER, I HAVE NO CLUE WHERE THE SPINNER ENTRY IS
            requestData.append("&sl=").append("en");
            out.write(String.valueOf(requestData));
            out.flush();

            urlConnection.connect();

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            if (inputStream == null)
                return null;

            translateJSONString = getStringFromBuffer(inputStream);

            // Catch errors.
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error" + e.getMessage());
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error" + e.getMessage());
                    return null;
                }

            }
        }

        // Return the JSON response.
        return translateJSONString;
    }

    /**
     * Runs on the background thread.
     *
     * @param voids No parameters in this use case.
     * @return Returns the string including the amount of time that
     * the background thread slept.
     */
    @Override
    protected String doInBackground(Void... voids) {

        System.out.println("Original text: " + mOriginalText);

        String translatedText = "Error";

        try {
            // Convert the response into a JSON object.
            String text = getTranslateText(""+mOriginalText);

            JSONObject jsonObject = new JSONObject(text);

            JSONObject information = jsonObject.getJSONObject("data");

            translatedText = information.get("translation").toString();

        } catch (Exception e){
            Log.e(LOG_TAG, "Error" + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Translated text: " + translatedText);

        // Return a String result
        return translatedText;
    }

    private String getStringFromBuffer(BufferedReader bufferedReader) {
        StringBuffer buffer = new StringBuffer();
        String line;

        if (bufferedReader != null) {
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line + '\n');
                }
                bufferedReader.close();
                return buffer.toString();
            } catch (Exception e) {
                Log.e("MainActivity", "Error" + e.getMessage());
                return null;
            } finally { }
        }
        return null;
    }

    /**
     * Does something with the result on the UI thread; in this case
     * updates the TextView.
     */
    protected void onPostExecute(String result) {
        mTextView.setText(result);
    }
}