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
     <ItemView/>
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

