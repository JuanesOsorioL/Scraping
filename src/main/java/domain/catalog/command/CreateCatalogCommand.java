package domain.catalog.command;

import domain.generic.Command;

public class CreateCatalogCommand extends Command {
    private String catalogId;
    private String name;

/*    public CreateCatalogCommand(String catalogId, String name) {
        this.catalogId = catalogId;
        this.name = name;
    }*/

    public CreateCatalogCommand() {
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
