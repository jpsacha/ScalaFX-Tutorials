/*
 * Copyright (c) 2011-2016, ScalaFX Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the ScalaFX Project nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE SCALAFX PROJECT OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package cell_factories.extras

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.cell.TextFieldTableCell
import scalafx.scene.control.{TableColumn, TableView}

//import scalafx.Includes._ // does not compile
//import scalafx.util.UtilIncludes.function12jfxCallback // does not compile
//import scalafx.Includes.{function12jfxCallback => _, _} // compiles OK


class Person(name_ : String) {

  val name = new StringProperty(this, "firstName", name_)
}

object SimpleTableView extends JFXApp {

  private val characters = ObservableBuffer[Person](
    new Person("Peggy"),
    new Person("Rocky")
  )

  stage = new PrimaryStage {
    title = "Simple Table View"
    scene = new Scene {
      content = new TableView[Person](characters) {
        columns ++= List(
          new TableColumn[Person, String] {
            text = "First Name"
            cellValueFactory = {
              _.value.name
            }
            cellFactory = (_: TableColumn[Person, String]) => new TextFieldTableCell[Person, String]()
            //            cellFactory = _ => new TextFieldTableCell[Person, String]()
            prefWidth = 180
          }
        )
      }
    }
  }
}
