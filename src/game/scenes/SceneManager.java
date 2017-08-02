package game.scenes;

public class SceneManager {
    private Scenes currenrScene;
    private Scenes nextScene;

    public static final SceneManager instance = new SceneManager();

    private SceneManager() {

    }

    public void changeSceneIfNeed() {
        if (nextScene !=  null) {
            if (currenrScene != null) {
                currenrScene.deInit();
            }
            nextScene.init();

            currenrScene = nextScene;
            nextScene = null;
        }
    }

    public void requestChangeScene(Scenes scenes) {
        nextScene = scenes;
    }
}
