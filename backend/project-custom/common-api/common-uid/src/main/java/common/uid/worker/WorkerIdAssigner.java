package common.uid.worker;

import common.uid.impl.DefaultUidGenerator;

/**
 * Represents a worker id assigner for {@link DefaultUidGenerator}
 *
 * @author zack <br>
 * @create 2021-06-23<br>
 * @project project-custom <br>
 */
public interface WorkerIdAssigner {

    /**
     * Assign worker id for {@link DefaultUidGenerator}
     *
     * @return assigned worker id
     */
    long assignWorkerId();
}
