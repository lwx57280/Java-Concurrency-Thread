package com.example.concurrency.queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockQueue implements Queue {

    //头结点
    private Node head;

    //尾结点
    private Node tail;

    //当前节点数
    private int num;

    //队列最大节点数
    private int size;

    private final ReentrantLock addLock = new ReentrantLock();

    private final Condition addLockCondition = addLock.newCondition();

    private final Condition pollLockCondition = addLock.newCondition();

    public MyBlockQueue(int size){
        this.size = size;
    }

    public MyBlockQueue(){
        this.size = 1000;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.num == 0;
    }

    public boolean isFull() {
        return this.num == this.size;
    }

    @Override
    public boolean contains(Object o) {
        Node t = this.head;
        if(t == null){
            return false;
        }else {
            do{
                if(o == t.getData()){
                    return true;
                }
                if(o.hashCode() == t.hashCode()){
                    return true;
                }
            }while ((t = t.getNext()) != null);
            return false;
        }
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean add(Object o) {
        addLock.lock();
        try {
            if(isFull()){
                System.out.println("队列已满，加不进去了。。。");
                // 阻塞当前线程
                addLockCondition.await();
            }
            if(contains(o)){
                throw new RuntimeException("该对象已经加入到队列中，不需要重复加。。。");
            }
            Node node = new Node();
            node.data = o;
            if(this.head == null){
                this.head = node;
                this.tail = node;
            }else {
                this.tail.next = node;
                this.tail = node;
            }
            this.num++;
            pollLockCondition.signal();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            addLock.unlock();
        }
        return false;
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public Object remove() {
        return null;
    }

    @Override
    public Object poll() {
        addLock.lock();
        try{
            if(this.head==null){
                System.out.println("队列已空，请等待。。。");
                pollLockCondition.await();
            }else {
                Node n = this.head;
                this.head = this.head.getNext();
                n.setNext(null);
                this.num--;
                addLockCondition.signal();
                return n.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            addLock.unlock();
        }
        return null;
    }

    @Override
    public Object element() {
        return null;
    }

    @Override
    public Object peek() {
        return null;
    }

    private class Node{
        private Object data;
        private Node next;

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
