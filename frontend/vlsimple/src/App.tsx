// src/App.tsx
import React from 'react';
import './App.css'; // Importando os estilos para a aplicação inteira
import Navbar from './components/navbar.tsx';
import Player from './components/player.tsx';
import Footer from './components/footer.tsx';

const App: React.FC = () => {
  return (
    <div className="App">
      <Navbar />
      <Player />
      <Footer />
    </div>
  );
};

export default App;
