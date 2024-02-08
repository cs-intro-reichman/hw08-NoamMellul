/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
        //// replace the following statement with your code
        if (this.size == this.maxSize) {// if the list is full
            return false;
        }
        this.tracks[size] = track;// add the given track to the end of the list
        this.size = size + 1;//modify the size of the list
        return true;
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        //// replace the following statement with your code
        StringBuilder str = new StringBuilder(size);// using string builder
        for(int i = 0; i < this.size; i++){
            str.append(this.getTrack(i)).append("\n"); // append the track at index i of the list to the string
        }
        return str.toString();
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() {
        //// replace this comment with your code
         if (this.size > 0){ // if the list is not empty
             tracks[size - 1] = null; // removing the last track
             this.size = size - 1; // modify the size of the list
         }

    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        //// replace the following statement with your code
        int res = 0;
        for(int i = 0; i < this.size; i++){
            res += this.getTrack(i).getDuration(); //add the time duration of each track in the list in res
        }
        return res;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) {
        //// replace the following statement with your code
        for(int i = 0; i < this.size; i++){
            if (title == this.getTrack(i).getTitle()){ // if the title is in the list
                return i ; // return the index
            }
        }
        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) {
        //// replace the following statement with your code
        if (i >= 0 && i <= this.size && this.size < this.maxSize) { // if the list is not empty and if i is not negative and if the list is not full
            if (this.size == i) { // if the index equals to the size
                this.add(track); // adding the track with the function add(track)
            }
            else {

                for (int j = this.size - 1; j >= i; j--) {
                    this.tracks[j + 1] = tracks[j];// we move all the tracks +1 to right
                }
                this.tracks[i] = track;// add the given track to the index i
                this.size = size + 1;// updates the size
            }
            return true;
        }
        return false;
    }
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) {
        //// replace this comment with your code
        if(i < 0 || i > this.size || this.size == 0){ // if the list is empty or the index is grater than the size of the index is negative
            return;
        }
        if (i == this.size - 1){// if i equals to size - 1
            this.removeLast();// removing the last track
        }
        else {
            this.tracks[i] = null; // removing the track at index i
            this.size = size -1;// update the size
            for(int j = i; j <= this.size; j++){
                this.tracks[j] = tracks[j + 1];// we move all tracks +1 to the left
            }
        }
    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) {
        //// replace this comment with your code
        // if the given track doesn't found of the list is empty or the given index is negative or too big for the list
        if(this.size == 0 || this.indexOf(title) == -1 || this.indexOf(title) > this.size){
            return;
        }
        for(int i = 0; i < this.size; i++){
            if(this.tracks[i].getTitle() == title){// we found the track in the list
                this.remove(this.indexOf(tracks[i].getTitle()));// removing it with the function remove(int i)
            }
        }
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() {
        //// replace this comment with your code
        this.remove(0);
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) {
        //// replace this comment with your code
         if(this.size + other.getSize() <= this.maxSize) {// if the total of the size of two playlist is lower than the maxsize
             for (int i = 0; i < other.size; i++) {
                 this.add(other.getTrack(i));// adding the tracks from the given playlist
             }
         }
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {
        //// replace the following statement with your code
        //if start is not negative and lower than size -1
        if(start >= 0 && start <= this.size - 1){
            //initializes min and minDuration with the value of start and the duration of the track at this index
            int min = start;
            int minDuration = this.tracks[start].getDuration();

            for(int i = start; i < this.size - 1; i++){
                int currentDuration = this.tracks[i].getDuration();// the current duration of the track at index i

                if(currentDuration < minDuration){
                    min = i;
                    minDuration = currentDuration;
                }
            }
            return min;
        }
        return -1;
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        // Uses the selection sort algorithm,  
        // calling the minIndex method in each iteration.
        //// replace this statement with your code
        for(int i = 0; i < this.size - 1; i++){
            int min = this.minIndex(i);// calling the min index method
            for (int j = i + 1; j < this.size; j++){
                // if the duration of the track at index j is lower than the track at index min
                if (this.tracks[j].getDuration() < this.tracks[min].getDuration()){
                    min = j;
                }
            }
            //swap
            Track temp = this.tracks[i];
            this.tracks[i] = this.tracks[min];
            this.tracks[min] = temp;
        }
    }
}
