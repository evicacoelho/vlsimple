import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import MediaList from './components/Media.tsx';
import PlaylistList from './components/Playlists.tsx';
import Navbar from './components/Navbar.tsx';
import Footer from './components/Footer.tsx';
import Player from './components/Player.tsx';

const App:React.FC = () => {
  return (
    <Router>
      <div>
        <Routes>
          <Route path='/play' element={
            <div className='App'>
              <Navbar />
              <Player />
              <Footer />
            </div>
          }/>
          <Route path="/media" element={<MediaList />} />
          <Route path="/playlists" element={<PlaylistList />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
