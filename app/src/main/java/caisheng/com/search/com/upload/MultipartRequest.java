package caisheng.com.search.com.upload;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class MultipartRequest extends Request<String> {

    private MultipartEntity entity = new MultipartEntity();

    private static final String FILE_PART_NAME = "file";
    private static final String STRING_PART_NAME = "text";

    private final Response.Listener<String> mListener;
    private final File[] mFilePart;

    public MultipartRequest(String url, Response.ErrorListener errorListener, Response.Listener<String> listener, File[] file)
    {
        super(Method.POST, url, errorListener);

        mListener = listener;
        mFilePart = file;
        buildMultipartEntity();
    }

    private void buildMultipartEntity()
    {
        for (int i = 0; i <mFilePart.length ; i++) {
            entity.addPart(FILE_PART_NAME, new FileBody(mFilePart[i]));
        }

       /* try
        {
            entity.addPart(STRING_PART_NAME, new StringBody(mStringPart));
        }
        catch (UnsupportedEncodingException e)
        {
            VolleyLog.e("UnsupportedEncodingException");
        }*/
    }

    @Override
    public String getBodyContentType()
    {
        return entity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try
        {
            entity.writeTo(bos);
        }
        catch (IOException e)
        {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response)
    {

        return Response.success(response.statusCode + "", getCacheEntry());
    }



    @Override
    protected void deliverResponse(String response)
    {
        mListener.onResponse(response);
    }
}