


/*


ConcurrentModificationException occurs when a collection is structurally modified while it is being iterated 
using an Iterator (directly or indirectly through a for-each loop), 
and the modification is not performed through the iterator itself.



    */


   /*Code at 142 line should be something like this as removing the data while iterating caused this issue */

   for (Transaction txn : transactions) {
    if (txn.isInvalid()) {
        transactions.remove(txn);
    }
}


    /* Changes */

/*
 * Here, the Iterator is created first and then used to iterate through
 * the collection. When an invalid transaction is found, itr.remove()
 * safely removes the current element from the collection.
 *
 * This does not throw ConcurrentModificationException because the removal
 * is performed through the Iterator itself rather than directly modifying
 * the collection (e.g., transactions.remove()) during iteration.
 */

Iterator<Transaction> itr = transactions.iterator();

while (itr.hasNext()) {
    Transaction txn = itr.next();

    if (txn.isInvalid()) {
        itr.remove();
    }
}

