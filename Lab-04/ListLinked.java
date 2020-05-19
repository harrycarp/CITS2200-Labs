import CITS2200.*;

public class ListLinked implements CITS2200.List
{
    private Link before;
    private Link after;

   //init empty list
    public ListLinked()
    {
        after = new Link(null, null);
        before = new Link(null, after); //linked to after
    }

    /**
     * Check if list is empty
     * @return true if so, false if otherwise
    **/
    public boolean isEmpty()
    {
        return before.successor == after;
    }

    /**
     *Check if window is matching before-first pos
     * @return true if such
    **/
    public boolean isBeforeFirst(WindowLinked w)
    {
        return w.link == before;
    }

    /**
     * Check if window is matching after-last pos
     * @return true if such
     **/
    public boolean isAfterLast(WindowLinked w)
    {
        return w.link == after;
    }

    /**
     * Init window to BF-pos
     * @param w WindowLinked
    **/
    public void beforeFirst(WindowLinked w)
    {
        w.link = before;
    }

    /**
     * Init window to AL-pos
     * @param w WindowLinked
    **/
    public void afterLast(WindowLinked w)
    {
        w.link = after;
    }

    /**
     * Move window to next position
     * @param w WindowLinked
     * @throws OutOfBounds if the window is past the end of the list
    **/
    public void next(WindowLinked w) throws OutOfBounds
    {
        if(!isAfterLast(w))
        {
            w.link = w.link.successor; 
        }
         else {
            throw new OutOfBounds("next: bad input for window.");
         }
    }

    /**
     * Move window to prev position
     * @param w WindowLinked
     * @throws OutOfBounds if the window is before the start of the list
    **/
    public void previous(WindowLinked w) throws OutOfBounds
    {
        if(!isEmpty() && !isBeforeFirst(w))
        {
            Link first = before; 
            while(first.successor != w.link) 
            {
                first = first.successor;
                if(first == after)
                    throw new OutOfBounds("Error navigating to previous element.");
            }
            w.link = first;
        }
         else {
            throw new OutOfBounds("Error the window is out of list bounds");
         }
    }

    /**
     * Insert element @e after window
     * @param e Object
     * @param w WindowLinked
     * @throws OutOfBounds if the window is beyond EOL and OOB
    **/
    public void insertAfter(Object e, WindowLinked w) throws OutOfBounds
    {
        if(w.link != null && !isAfterLast(w))
        {
            Link next = new Link(e, w.link.successor);
            w.link.successor = next;
        }
         else {
            throw new OutOfBounds("insert after: window is not in the list.");
         }
    }

    /**
     * insert element @e before window
     * @param e Object
     * @param w WindowLinked
     * @throws OutOfBounds if the window is before SOL and OOB
    **/
    public void insertBefore(Object e, WindowLinked w) throws OutOfBounds
    {
        if(!isBeforeFirst(w) && w.link != null)
        {
            Link cWindow = w.link;
            Link next = new Link(w.link.item, w.link.successor); 
            w.link.successor = next; 
            w.link.item = e;
            if(w.link == after)
                after = next;
            w.link = next;
        } else {
            throw new OutOfBounds("Window is before the start of the list");
        }
            
    }

    /**
     * Examines the element in the window
     * @return Object element under the window
     * @throws OutOfBounds if the window is outside the list
     * @param w WindowLinked
    **/
    public Object examine(WindowLinked w) throws OutOfBounds
    {
        if(w.link != null && !isBeforeFirst(w) && !isAfterLast(w))
        {
            return w.link.item;
        } else {
            throw new OutOfBounds("window is outside the list.");
        }
    }

    /**
     * Deletes the element under the window and places the window
     * over the next element.
     * @return Object the old element under the window.
     * @param w WindowLinked
     * @throws OutOfBounds if the window is over the before first position,
     * before the first element or the linked list is empty.
    **/
    public Object delete(WindowLinked w) throws OutOfBounds
    {
      if(w.link != null && !isBeforeFirst(w) && !isAfterLast(w))
      {
          if(after == w.link.successor)
          {
              after = w.link;
              return w.link.item;
          }
          Object element = w.link.item;
          w.link.item = w.link.successor.item;
          w.link.successor = w.link.successor.successor; 
          return element;
      }
      else
          throw new OutOfBounds("window is outside of list.");
    }
    /**
     * Replaces the element under the window
     * @return the old element under the window of type E
     * @param e Object
     * @param w WindowLinked
     * @throws OutOfBounds if the window is over the before first position,
     * after last positions or empty.
    **/
    public Object replace(Object e, WindowLinked w) throws OutOfBounds
    {
        if(w.link != null && !isBeforeFirst(w) && !isAfterLast(w))
        {
            Object element = w.link.item;
            w.link.item = e;
            return element;
        }
        else
            throw new OutOfBounds("window is outside the list.");
    }

    
}