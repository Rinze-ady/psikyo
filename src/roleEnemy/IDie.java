package roleEnemy;

/**
 * 怪物死亡后如果要完成业务，需要实现该接口
 */
public interface IDie {

    /**
     * 怪物死亡后，需要完成的业务
     */
    public void leaveOver();
}
