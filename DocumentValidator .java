    import java.util.List;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;


    // As it is nothing mentioned about ValidationResult and Document classes, I am assuming they are defined elsewhere in the codebase where
    // the Document class has a method extractContent() and the ValidationResult class has methods invalid() and isValid().

    public class DocumentValidator {

        private static final Logger logger =
                LoggerFactory.getLogger(DocumentValidator.class);

        public ValidationResult validate(Document doc) {

            try {

                if (doc == null) {

                    // Expected validation failure should not be treated
                    // as an unexpected runtime exception
                    logger.warn("Validation failed: document is null");

                    return ValidationResult.invalid("Document is null");
                }

                String content = doc.extractContent();

                if (content == null || content.isEmpty()) {

                    // Expected validation failure should return a
                    // validation result instead of throwing RuntimeException
                    logger.warn("Validation failed: empty content");

                    return ValidationResult.invalid("Empty content");
                }

                return runValidationRules(content);

            } catch (Exception e) {

                //  Replace printStackTrace() with structured logging
                logger.error("Unexpected error during validation", e);

                //  Never return null; return a failure result
                return ValidationResult.invalid("Validation processing failed");
            }
        }

        public void validateBatch(List<Document> docs) {

            for (Document doc : docs) {

                try {

                    ValidationResult r = validate(doc);

                    //  Prevent NullPointerException and only save valid results
                    if (r != null && r.isValid()) {
                        saveResult(r);
                    }

                } catch (Exception e) {

                    // Do not swallow exceptions silently; log them
                    logger.error("Error while processing document in batch", e);
                }
            }
        }

        private ValidationResult runValidationRules(String content) {
            return null;
        }

        private void saveResult(ValidationResult result) {
        }
    }
