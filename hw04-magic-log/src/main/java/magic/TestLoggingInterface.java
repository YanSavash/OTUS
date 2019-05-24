package magic;

import Annotations.Log;

public interface TestLoggingInterface {

    @Log(mark = "true")
    public void calculation(int param);

}
