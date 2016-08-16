/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.bookstore.tests.selenium.pages.score;

import co.edu.uniandes.csw.bookstore.dtos.minimum.ScoreDTO;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ScoreCreatePage {

    @FindBy(id = "score")
    private WebElement scoreInput;
    @FindBy(id = "description")
    private WebElement descriptionInput;

    @FindBy(id = "save-score")
    private WebElement saveBtn;

    @FindBy(id = "cancel-score")
    private WebElement cancelBtn;

    public void saveScore(ScoreDTO score) {
         waitGui().until().element(scoreInput).is().visible();
         scoreInput.clear();
         scoreInput.sendKeys(score.getScore().toString());
         waitGui().until().element(descriptionInput).is().visible();
         descriptionInput.clear();
         descriptionInput.sendKeys(score.getDescription());
        guardAjax(saveBtn).click();
    }
}
