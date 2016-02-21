package caisheng.com.search.com.upload;

import android.app.Activity;
import android.app.Dialog;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import caisheng.com.search.R;

public class ChoosePicActivity extends Activity {
    GridView gridView;
    ImageAdapter adapter;
    private boolean hasFolderGened = false;
    FoldList foldAdapter;
    // 文件夹数据
    private ArrayList<Folder> mResultFolder = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pic);
        gridView = (GridView) findViewById(R.id.gridView);
        getLoaderManager().initLoader(0, null, mLoaderCallback);
        adapter = new ImageAdapter(this);
        gridView.setAdapter(adapter);
        foldAdapter=new FoldList(this);
    }

    public void choose(View v) {

        final Dialog dialog = new Dialog(this);
        View list = getLayoutInflater().inflate(R.layout.all_select, null);
        ListView listPic = (ListView) list.findViewById(R.id.listView3);
        listPic.setAdapter(foldAdapter);
        listPic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplication(),"as",Toast.LENGTH_SHORT).show();
                adapter.setData(mResultFolder.get(position).list);
                dialog.dismiss();
            }
        });

        dialog.setContentView(list);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
        dialog.show();
    }


    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {

        private final String[] IMAGE_PROJECTION = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media._ID};

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            if (id == 0) {
                CursorLoader cursorLoader = new CursorLoader(ChoosePicActivity.this,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
                        null, null, IMAGE_PROJECTION[2] + " DESC");
                return cursorLoader;
            } else if (id == 1) {
                CursorLoader cursorLoader = new CursorLoader(ChoosePicActivity.this,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
                        IMAGE_PROJECTION[0] + " like '%" + args.getString("path") + "%'", null, IMAGE_PROJECTION[2] + " DESC");
                return cursorLoader;
            }

            return null;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (data != null) {
                ArrayList<Image> images = new ArrayList<>();
                int count = data.getCount();
                if (count > 0) {
                    data.moveToFirst();
                    do {
                        String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                        String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                        long dateTime = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
                        Image image = new Image();
                        image.name=name;
                        image.path=path;
                        images.add(image);
                        if (!hasFolderGened) {
                            // 获取文件夹名称
                            File imageFile = new File(path);
                            File folderFile = imageFile.getParentFile();
                            Folder folder = new Folder();
                            folder.name = folderFile.getName();
                            folder.path = folderFile.getAbsolutePath();
                            folder.image = image;
                            if (!mResultFolder.contains(folder)) {
                                ArrayList<Image> imageList = new ArrayList<>();
                                imageList.add(image);
                                folder.list = imageList;
                                mResultFolder.add(folder);
                            } else {
                                // 更新
                                Folder f = mResultFolder.get(mResultFolder.indexOf(folder));
                                f.list.add(image);
                            }
                        }

                    } while (data.moveToNext());

                    adapter.setData(images);

                /*    // 设定默认选择
                    if (resultList != null && resultList.size() > 0) {
                        adapter.setDefaultSelected(resultList);
                    }*/

                    foldAdapter.setData(mResultFolder);
                    hasFolderGened = true;

                }
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };


}
