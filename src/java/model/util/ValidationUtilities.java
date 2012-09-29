package model.util;

/**
 *
 * @author skuarch
 */
public class ValidationUtilities {

    //==========================================================================
    /**
     * validate if everything is ok when someone do click in the trees.
     *
     * @param view String
     * @param collector String
     * @return boolean
     */
    public static boolean validateClickTree(String view, String collector, String job) throws Exception {

        boolean flag = true;
        
        if (view == null || collector == null || job == null) {
            return false;
        }

        //validate if job contains the word job
            /*if(!job.contains("job")){
         return false;
         }*/
        
        if(!job.contains("without")){
         return false;
         }

        //validate if the collector is equals to "servers"
        if (collector.equalsIgnoreCase("servers")) {
            //"servers" doesn't work, I need a collector name
            return false;
        }

        //validate if is a categorie
        if (CategoriesUtilities.exitsCategorie(view)) {
            //this means that the selection in tree view is a category.
            return false;
        }

        return flag;

    } // end validateClickTree
} // end class
