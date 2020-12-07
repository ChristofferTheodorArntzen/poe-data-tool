import logo from './logo.svg' 
import './App.css';
import ItemView from './ItemView';
import ButtonsView from './ButtonsView';

// remove when we adpat redux to our components
import React from "react";
import List from "./List";
import Form from "./Form";

const App = () => (
  <>
    <div>
      <h2>Articles</h2>
      <List />
    </div>
    <div>
      <h2>Add a new article</h2>
      <Form />
    </div>
  </>
);

export default App;


//function App() {

//  return (
//    <div className="App" style={{ backgroundColor: '#222' }}>
//       <ButtonsView/>
//     </div>
      
//   );
// }
// export default App;

// https://www.valentinog.com/blog/redux/

