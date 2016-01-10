package caisheng.com.search;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

public class TransitionsActivity extends Activity {
    Scene scene1,scene2;
    TransitionManager transitionManager;
    ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transitions);
        viewGroup=(ViewGroup)findViewById(R.id.lin);
        TransitionInflater inflater=TransitionInflater.from(this);
        scene1=Scene.getSceneForLayout(viewGroup,R.layout.view1,this);
        scene2=Scene.getSceneForLayout(viewGroup,R.layout.view2,this);

        transitionManager=inflater.inflateTransitionManager(R.transition.transitions_mgr,viewGroup);


    }

    public void trans(View v){
        transitionManager.transitionTo(scene1);
    }

    public void trans1(View v){
        transitionManager.transitionTo(scene2);
    }
}
