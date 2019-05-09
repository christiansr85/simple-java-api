package employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Database collection to handle sequence for user ids.
 */
@Document(collection = "database_sequences")
public class DatabaseSequence {
 
    /**
     * Id of the sequence which provides of the numeric user id.
     */
    @Id
    private String id;
 
    /**
     * The value to assign to the next record to insert in database.
     */
    private long seq;

    /**
     * Gets the sequence's id.
     * @return The sequence's id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the squence's id.
     * @param id The new sequence's id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the next numeric value in the sequence.
     * @return The next numeric value in the sequence.
     */
    public long getSeq() {
        return this.seq;
    }

    /**
     * Sets the new numeric value of the sequence.
     * @param seq The new numeric value of the sequence.
     */
    public void setSeq(long seq) {
        this.seq = seq;
    }
}
