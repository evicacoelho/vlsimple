import React from 'react';

interface MediaPlayerProps {
  filePath: string;
}

const MediaPlayer: React.FC<MediaPlayerProps> = ({ filePath }) => {
  const fileType = filePath.split('.').pop()?.toLowerCase();

  return (
    <div>
      {fileType === 'mp3' || fileType === 'wav' ? (
        <audio controls>
          <source src={filePath} type={`audio/${fileType}`} />
          Your browser does not support the audio element.
        </audio>
      ) : fileType === 'mp4' || fileType === 'webm' ? (
        <video controls>
          <source src={filePath} type={`video/${fileType}`} />
          Your browser does not support the video element.
        </video>
      ) : (
        <p>Unsupported file type</p>
      )}
    </div>
  );
};

export default MediaPlayer;
